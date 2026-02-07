package org.mdxq.wwjclub.cms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.dao.DirectionMapper;
import org.mdxq.wwjclub.cms.dto.DirectionInsertDTO;
import org.mdxq.wwjclub.cms.dto.DirectionPageDTO;
import org.mdxq.wwjclub.cms.dto.DirectionUpdateDTO;
import org.mdxq.wwjclub.cms.service.DirectionService;
import org.mdxq.wwjclub.cms.vo.DirectionVO;
import org.mdxq.wwjclub.entity.Direction;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明：方向业务实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:18
 */
@Service
@CacheConfig(cacheNames = "direction")//配置Redis缓存名
public class DirectionServiceImpl implements DirectionService {

    @Resource
    private DirectionMapper directionMapper;

    @Override
    public boolean save(DirectionInsertDTO dto) {

        Direction Direction = BeanUtil.copyProperties(dto, Direction.class);
        if (ObjectUtil.isEmpty(dto.getInfo())) {
            Direction.setInfo("暂无介绍");
        }
        if (directionMapper.insert(Direction) == 0) {
            throw new ServerErrorException("DB：方向插入失败");
        }
        return true;
    }

    @Override
    @Retryable(retryFor = VersionException.class)
    public boolean update(DirectionUpdateDTO dto) {
        Direction Direction = directionMapper.selectById(dto.getId());
        if (Direction == null) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        BeanUtil.copyProperties(dto, Direction);
        if (directionMapper.update(Direction) == 0) {
            throw new VersionException("DB：方向更新失败");
        }
        return true;
    }

    @Override
    public Direction getById(Long id) {
        Direction Direction = directionMapper.selectById(id);
        if (Direction == null) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        return Direction;
    }

    @Override
    public boolean deleteById(Long id) {
        if (directionMapper.delete(id) == 0) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        return true;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        if (directionMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB：批量删除失败");
        }
        return true;
    }

    @Override
    public PageInfo<Direction> page(DirectionPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(directionMapper.list(dto));
    }

    @Override
    public List<DirectionVO> listAll() {
        return directionMapper.listAll();
    }
}
