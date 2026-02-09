package org.mdxq.wwjclub.rms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.vo.ClubVO;
import org.mdxq.wwjclub.entity.Room;
import org.mdxq.wwjclub.entity.School;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.rms.dao.RoomMapper;
import org.mdxq.wwjclub.rms.dao.SchoolMapper;
import org.mdxq.wwjclub.rms.dto.*;
import org.mdxq.wwjclub.rms.service.RoomService;
import org.mdxq.wwjclub.rms.service.SchoolService;
import org.mdxq.wwjclub.rms.vo.SchoolVO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类说明：学校信息业务接口实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 14:44
 */
@Service
@CacheConfig(cacheNames = "room")//配置Redis缓存名
public class SchoolServiceImpl implements SchoolService {
    // ctrl +  i 实现所有方法

    @Resource
    private SchoolMapper schoolMapper;


    @Override
    @CacheEvict(allEntries = true)//插入新值后缓存失效
    public boolean save(SchoolInsertDTO dto) {
        String address = dto.getAddress();
        String info = dto.getInfo();
        School school = BeanUtil.copyProperties(dto, School.class);
        // 设置地址和介绍的默认值
        if(ObjectUtil.isEmpty(address)) school.setAddress("暂未填写");
        if(ObjectUtil.isEmpty(info)) school.setInfo("暂未填写");
        if (schoolMapper.insert(school) == 0) {
            throw new ServerErrorException("DB: 添加学校记录失败");
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional
    @Retryable(retryFor = VersionException.class)
    public boolean update(SchoolUpdateDTO dto) {
        School school = schoolMapper.selectById(dto.getId());
        if(ObjectUtil.isNull(school)){
            throw new ServerErrorException("DB: 该学校不存在或已删除");
        }
        BeanUtil.copyProperties(dto, school);//从dto中复制属性新值
        if(schoolMapper.update(school) == 0) {
            throw new VersionException("DB: 更新学校失败");
        }
        return true;
    }

    @Override
    @Cacheable(key = "#p0",condition = "#p0 != null",unless="#result==null")
    public School getById(Long id) {
        School school = schoolMapper.selectById(id);
        if(school == null) throw new ServerErrorException("记录不存在");
        return school;
    }

    @Override
    @CacheEvict(key = "#p0",condition = "#result==true")
    public boolean deleteById(Long id) {
        if(schoolMapper.delete(id) == 0)
            throw new ServerErrorException("删除记录失败");
        return true;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true,condition = "result==true")
    public boolean deleteBatch(List<Long> ids) {
        if(schoolMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 批量删除失败，所有记录将被回滚");
        }
        return true;
    }

    @Override
    public PageInfo<School> page(SchoolPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(schoolMapper.list(dto));
    }

    @Override
    public List<SchoolVO> listAll() {
        return schoolMapper.listAll();
    }
}
