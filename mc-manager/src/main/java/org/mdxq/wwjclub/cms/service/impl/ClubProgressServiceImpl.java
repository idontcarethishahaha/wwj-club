package org.mdxq.wwjclub.cms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.mdxq.wwjclub.cms.dao.ClubProgressMapper;
import org.mdxq.wwjclub.cms.dto.ClubProgressInsertDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressPageDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressUpdateDTO;
import org.mdxq.wwjclub.cms.service.ClubProgressService;
import org.mdxq.wwjclub.entity.ClubProgress;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明：班级进度业务实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 14:44
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "clubProgress")//配置Redis缓存名
public class ClubProgressServiceImpl implements ClubProgressService {
    // ctrl +  i 实现所有方法
    @Resource//注入一个Mapper依赖
    private ClubProgressMapper clubProgressMapper;

    @Override
    public boolean save(ClubProgressInsertDTO dto) {
        ClubProgress clubProgress = BeanUtil.copyProperties(dto, ClubProgress.class);
        if (StrUtil.isEmpty(dto.getInfo())){
            clubProgress.setInfo("暂无介绍");
        }
        // important
        BeanUtil.copyProperties(dto, clubProgress);
        if(clubProgressMapper.insert(clubProgress)==0){
            throw new ServerErrorException("DB: 插入新班级进度失败");
        }
        return true;
    }

//    @Override
//    public boolean update(ClubProgressUpdateDTO dto) {
//        ClubProgress ClubProgress = clubProgressMapper.selectById(dto.getId());
//        if (ClubProgress == null) {
//            throw new ServerErrorException("DB：记录不存在或已删除");
//        }
//        BeanUtil.copyProperties(dto, ClubProgress);
//        if (clubProgressMapper.update(ClubProgress) == 0) {
//            throw new VersionException("DB：班级进度更新失败");
//        }
//        return true;
//    }

    @Override
    public boolean update(ClubProgressUpdateDTO dto) {
        log.info("更新班级进度，DTO参数：{}", dto);
        ClubProgress clubProgress = clubProgressMapper.selectById(dto.getId());
        log.info("查询到原记录：{}", clubProgress);
        BeanUtil.copyProperties(dto, clubProgress, true);
        if (StrUtil.isEmpty(clubProgress.getInfo())) {
            clubProgress.setInfo("暂无介绍");
        }
        log.info("更新后的记录：{}", clubProgress);
        int updateCount = clubProgressMapper.update(clubProgress);
        log.info("更新行数：{}", updateCount);
        if (updateCount == 0) {
            throw new VersionException("DB：班级进度更新失败（更新行数为0）");
        }
        return true;
    }

    @Override
    public ClubProgress getById(Long id) {
        ClubProgress clubProgress = clubProgressMapper.selectById(id);
        if (clubProgress == null) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        return clubProgress;
    }

    @Override
    public boolean deleteById(Long id) {
        if (clubProgressMapper.delete(id) == 0) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        return true;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        if (clubProgressMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB：批量删除失败");
        }
        return true;
    }

    @Override
    public PageInfo<ClubProgress> page(ClubProgressPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(clubProgressMapper.list(dto));
    }
}
