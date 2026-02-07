package org.mdxq.wwjclub.cms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.cms.dto.ClubProgressInsertDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressPageDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressUpdateDTO;
import org.mdxq.wwjclub.entity.ClubProgress;

import java.util.List;

/**
 * 类说明：班级进度业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/5 10:18
 */
public interface ClubProgressService {
    boolean save(ClubProgressInsertDTO dto);
    boolean update(ClubProgressUpdateDTO dto);
    ClubProgress getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<ClubProgress> page(ClubProgressPageDTO dto);
}