package org.mdxq.wwjclub.ums.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.EmpRole;
import org.mdxq.wwjclub.entity.Role;
import org.mdxq.wwjclub.mapper.BaseMapper;
import org.mdxq.wwjclub.ums.dto.EmpInsertDTO;
import org.mdxq.wwjclub.ums.vo.RoleVO;

import java.util.List;

/**
 * 接口说明：角色信息数据访问层
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 9:31
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role>{

    List<RoleVO> listAll();

    int countByTitle(String title);

    //查询员工的角色列表
    List<RoleVO> listByEmpId(Long empId);

    //修改用户角色

   //删除员工已有角色
    int deleteRoleByEmpId(Long empId);

    // 批量添加员工 - 角色
    int insertEmpRoleBatch(List<EmpRole> empRoles);

    //===========================================

    @Override
    int insert(Role role);

    @Override
    int update(Role role);

    @Override
    int delete(Long id);

    @Override
    int deleteBatch(List<Long> ids);

    @Override
    Role selectById(Long id);

    @Override
    List<Role> list(PageDTO pageDTO);
}
