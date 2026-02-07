package org.mdxq.wwjclub.cms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.dao.ClubProgressMapper;
import org.mdxq.wwjclub.cms.dto.ClubProgressInsertDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressPageDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressUpdateDTO;
import org.mdxq.wwjclub.cms.service.ClubProgressService;
import org.mdxq.wwjclub.entity.ClubProgress;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/5 15:51
 */
//@Service
//public class ClubProgressServiceImpl0 implements ClubProgressService {
//    @Resource
//    private ClubProgressMapper progressMapper;
//
//    @Override
//    public boolean save(ClubProgressInsertDTO dto) {
//        ClubProgress clubProgress = BeanUtil.copyProperties(dto, ClubProgress.class);
//        if (StrUtil.isBlank(dto.getInfo())) {
//            clubProgress.setInfo("暂无介绍");
//        }
//        if (progressMapper.insert(clubProgress) == 0) {
//            throw new ServerErrorException("DB: 添加记录失败");
//        }
//        return true;
//    }
//
//    @Override
//    @Retryable(retryFor = VersionException.class)
//    public boolean update(ClubProgressUpdateDTO dto) {
//        ClubProgress clubProgress = progressMapper.selectById(dto.getId());
//        if (clubProgress == null) {
//            throw new ServerErrorException("DB: 记录不存在或已删除");
//        }
//        BeanUtil.copyProperties(dto, clubProgress);
//        if (progressMapper.update(clubProgress) == 0) {
//            throw new VersionException("DB: 记录更新失败");
//        }
//        return true;
//    }
//
//    @Override
//    public ClubProgress getById(Long id) {
//        ClubProgress clubProgress = progressMapper.selectById(id);
//        if (clubProgress == null) {
//            throw new ServerErrorException("DB: 记录不存在或已删除");
//        }
//        return clubProgress;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        if (progressMapper.delete(id) == 0) {
//            throw new ServerErrorException("DB: 记录删除失败");
//        }
//        return true;
//    }
//
//    @Override
//    public boolean deleteBatch(List<Long> ids) {
//        if (progressMapper.deleteBatch(ids) < ids.size()) {
//            throw new ServerErrorException("DB: 记录批量删除失败");
//        }
//        return true;
//    }
//
//    @Override
//    public PageInfo<ClubProgress> page(ClubProgressPageDTO dto) {
//        PageHelper.startPage(dto);
//        return new PageInfo<>(progressMapper.list(dto));
//    }
//}
