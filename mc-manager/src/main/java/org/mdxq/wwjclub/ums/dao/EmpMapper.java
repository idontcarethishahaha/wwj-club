package org.mdxq.wwjclub.ums.dao;

import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.Emp;
import org.mdxq.wwjclub.mapper.BaseMapper;

import java.util.List;


/**
 * 接口说明：员工Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/28 15:11
 */
public interface EmpMapper extends BaseMapper<Emp> {

    @Override
    List<Emp> list(PageDTO pageDTO);

    @Override
    Emp selectById(Long id);
}
