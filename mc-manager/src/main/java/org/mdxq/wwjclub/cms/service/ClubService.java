package org.mdxq.wwjclub.cms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.cms.dto.ClubInsertDTO;
import org.mdxq.wwjclub.cms.dto.ClubPageDTO;
import org.mdxq.wwjclub.cms.dto.ClubUpdateDTO;
import org.mdxq.wwjclub.cms.vo.ClubVO;
import org.mdxq.wwjclub.entity.Club;

import java.util.List;

/**
 * 类说明：班级业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/5 10:18
 */
public interface ClubService {
    boolean save(ClubInsertDTO dto);
    boolean update(ClubUpdateDTO dto);
    Club getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Club> page(ClubPageDTO dto);
    List<ClubVO> listAll();
}