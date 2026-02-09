package org.mdxq.wwjclub.rms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mdxq.wwjclub.cms.vo.ClubVO;
import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.School;
import org.mdxq.wwjclub.mapper.BaseMapper;
import org.mdxq.wwjclub.rms.vo.SchoolVO;

import java.util.List;

/**
 * 接口说明：学校信息数据访问层
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 11:33
 */
@Mapper
public interface SchoolMapper extends BaseMapper<School> {
    // ctrl + i
    // ctrl + o 更方便
    @Override
    int insert(School school);

    @Override
    School selectById(Long id);

    @Override
    int update(School school);

    @Override
    int delete(Long id);

    @Override
    int deleteBatch(List<Long> ids);

    @Override
    List<School> list(PageDTO pageDTO);

    List<SchoolVO> listAll();

}

