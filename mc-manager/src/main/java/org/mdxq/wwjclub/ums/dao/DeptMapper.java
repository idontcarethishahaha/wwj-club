package org.mdxq.wwjclub.ums.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.Dept;
import org.mdxq.wwjclub.mapper.BaseMapper;
import org.mdxq.wwjclub.ums.vo.DeptVO;

import java.util.List;


/**
 * 接口说明：部门Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/28 15:11
 */
@Mapper
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

    //查询所有的部门
    List<DeptVO> listAll();

    // 使用title查询部门
    long countByTitle(String title);
}
