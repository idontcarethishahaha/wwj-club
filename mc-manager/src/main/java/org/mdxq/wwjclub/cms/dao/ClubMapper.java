package org.mdxq.wwjclub.cms.dao;

import org.mdxq.wwjclub.cms.vo.ClubVO;
import org.mdxq.wwjclub.entity.Club;
import org.mdxq.wwjclub.mapper.BaseMapper;

import java.util.List;

/**
 * 接口说明：班级Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/3 11:49
 */
public interface ClubMapper extends BaseMapper<Club> {
    List<ClubVO> listAll();
}
