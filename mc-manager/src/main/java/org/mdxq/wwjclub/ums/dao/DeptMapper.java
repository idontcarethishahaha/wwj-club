package org.mdxq.wwjclub.ums.dao;

import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.Dept;
import org.mdxq.wwjclub.entity.Emp;
import org.mdxq.wwjclub.entity.Dept;
import org.mdxq.wwjclub.mapper.BaseMapper;

import java.util.List;


/**
 * 接口说明：员工Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/28 15:11
 */
public interface DeptMapper extends BaseMapper<Dept> {

    @Override
    int insert(Dept dept);

    @Override
    int update(Dept dept);

    @Override
    int delete(Long id);

    @Override
    int deleteBatch(List<Long> ids);

    @Override
    Dept selectById(Long id);

    @Override
    List<Dept> list(PageDTO pageDTO);
}
