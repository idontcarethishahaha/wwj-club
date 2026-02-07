package org.mdxq.wwjclub.cms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.dao.ClubMapper;
import org.mdxq.wwjclub.cms.dto.ClubInsertDTO;
import org.mdxq.wwjclub.cms.dto.ClubPageDTO;
import org.mdxq.wwjclub.cms.dto.ClubUpdateDTO;
import org.mdxq.wwjclub.cms.service.ClubService;
import org.mdxq.wwjclub.cms.vo.ClubVO;
import org.mdxq.wwjclub.entity.Club;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明：班级业务实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/5 10:19
 */
@Service
public class ClubServiceImpl implements ClubService {
    @Resource
    private ClubMapper clubMapper;

//    @Override
//    public boolean save(ClubInsertDTO dto) {
//        Club club = BeanUtil.copyProperties(dto, Club.class);
//        if (clubMapper.insert(club) == 0) {
//            throw new ServerErrorException("DB: 添加班级失败");
//        }
//        return true;
//    }

    @Override
    public boolean save(ClubInsertDTO dto) {
        List<String> tag = dto.getTag();
        Club club = BeanUtil.copyProperties(dto, Club.class);
        if (StrUtil.isEmpty(dto.getInfo())) {
            club.setInfo("暂无介绍");
        }
        if (CollUtil.isEmpty(tag)) {
            club.setTag("暂无标签");
        } else {
            club.setTag(String.join(",", tag));
        }
        if (clubMapper.insert(club) == 0) {
            throw new ServerErrorException("DB: 添加班级失败");
        }
        return true;
    }

    @Override
    @Retryable(retryFor = VersionException.class)
    public boolean update(ClubUpdateDTO dto) {
        Club club = clubMapper.selectById(dto.getId());
        if (club == null) {
            throw new ServerErrorException("DB: 记录不存在或已删除");
        }

        List<String> tag = dto.getTag();
        BeanUtil.copyProperties(dto, club);
        if (CollUtil.isEmpty(tag)) {
            club.setTag("暂无标签");
        } else {
            club.setTag(String.join(",", tag));
        }
        if (clubMapper.update(club) == 0) {
            throw new VersionException("DB: 修改班级失败");
        }
        return true;
    }

//    @Override
//    @Retryable(retryFor = VersionException.class)
//    public boolean update(ClubUpdateDTO dto) {
//        Club club = clubMapper.selectById(dto.getId());
//        if (club == null) {
//            throw new ServerErrorException("DB: 记录不存在或已删除");
//        }
//        BeanUtil.copyProperties(dto, club);
//        if (clubMapper.update(club) == 0) {
//            throw new VersionException("DB: 修改班级失败");
//        }
//        return true;
//    }

    @Override
    public Club getById(Long id) {
        Club club = clubMapper.selectById(id);
        if (club == null) {
            throw new ServerErrorException("DB: 记录不存在或已删除");
        }
        return club;
    }

    @Override
    public boolean deleteById(Long id) {
        if (clubMapper.delete(id) == 0) {
            throw new ServerErrorException("DB: 删除记录失败");
        }
        return true;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        if (clubMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 批量删除记录失败");
        }
        return true;
    }

    @Override
    public PageInfo<Club> page(ClubPageDTO dto) {
        PageHelper.startPage(dto);
        return new PageInfo<>(clubMapper.list(dto));
    }

    @Override
    public List<ClubVO> listAll() {
        return clubMapper.listAll();
    }
}
