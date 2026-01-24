package org.mdxq.wwjclub.rms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Room;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.rms.dao.RoomMapper;
import org.mdxq.wwjclub.rms.dto.RoomInsertDTO;
import org.mdxq.wwjclub.rms.dto.RoomPageDTO;
import org.mdxq.wwjclub.rms.dto.RoomUpdateDTO;
import org.mdxq.wwjclub.rms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类说明：房间信息管理实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 14:44
 */
@Service
@CacheConfig(cacheNames = "room")//配置Redis缓存名
public class RoomServiceImpl implements RoomService {
    // ctrl +  i 实现所有方法
    //@Autowired 报错
    @Resource//注入一个Mapper依赖
    private RoomMapper roomMapper;

    @Override
    @CacheEvict(allEntries = true)//让当前Room相关的缓存过期
    public boolean save(RoomInsertDTO dto) {
        String info = dto.getInfo();//提取房间信息

        // 将dto中的属性拷贝，得到一个 Room 对象
        Room room = BeanUtil.copyProperties(dto, Room.class);
        room.setInfo(ObjectUtil.isEmpty(info) ? "暂无介绍" : info);
//        if(ObjectUtil.isEmpty(info)){
//            room.setInfo("暂无介绍");
//        }
        room.setVersion(0L);//初始化乐观锁的版本号
        int result = roomMapper.insert(room);//将房间信息插入数据库
        if (result == 0) {
            throw new ServerErrorException("添加记录失败");
        }
        // 如果添加成功返回 true
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)//所有Room缓存失效
    @Transactional//声明式事务注解
    @Retryable(retryFor = VersionException.class)
    public boolean update(RoomUpdateDTO dto) {

//        Room room = BeanUtil.copyProperties(dto, Room.class);
//        //更新Room记录的最后修改时间
//        room.setUpdated(LocalDateTime.now());
//        if(roomMapper.selectById(dto.getId()) == null) {
//            throw new ServerErrorException("记录不存在或已删除");
//        }
        Room room = roomMapper.selectById(dto.getId());
        if(room == null){
            throw new ServerErrorException("记录不存在或已删除");
        }
        // 用DTO中的属性覆盖查询到的room
        BeanUtil.copyProperties(dto, room);
        // 更新room的修改时间
        room.setUpdated(LocalDateTime.now());
        int result = roomMapper.update(room);//更新记录
        if(result == 0) {
            //触发Spring的重试机制，重新尝试更新记录
            throw new VersionException("更新失败：版本号错误");
        }
        //更新成功了返回true
        return true;
    }

    @Override
    // 将查询结果写入 Redis 缓存，参数0（id）做key，除非未查到结果
    @Cacheable(key = "#p0",condition = "#p0 != null",unless = "#result == null")
    public Room getById(Long id) {
        Room room = roomMapper.selectById(id);
        if(room == null) {
            throw new ServerErrorException("记录不存在");
        }
        return room;
    }

    @Override
    @CacheEvict(allEntries = true)//触发所有缓存失效
    public boolean deleteById(Long id) {
        int result = roomMapper.delete(id);
        if(result == 0) {
            throw new ServerErrorException("DB: 记录删除失败");
        }
        return true;
    }

    @Override
    @Transactional//事务注解保证批量删除操作要么都成功，要么都失败
    @CacheEvict(allEntries = true)
    public boolean deleteBatch(List<Long> ids) {
        int n = roomMapper.deleteBatch(ids);
        if(n < ids.size()) {
            throw new ServerErrorException("DB: 记录删除失败");
        }
        return true;
    }

    @Override
    public PageInfo<Room> page(RoomPageDTO dto) {
        // 开始分页
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        // 自动将查询到的房间列表封装成分页对象
        return new PageInfo<>(roomMapper.list(dto.getTitle()));
    }
}
