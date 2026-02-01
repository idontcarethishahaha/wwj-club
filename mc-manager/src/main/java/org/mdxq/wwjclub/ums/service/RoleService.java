package org.mdxq.wwjclub.ums.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.Role;
import org.mdxq.wwjclub.ums.dto.RoleInsertDTO;
import org.mdxq.wwjclub.ums.dto.RolePageDTO;
import org.mdxq.wwjclub.ums.dto.RoleUpdateDTO;
import org.mdxq.wwjclub.ums.vo.RoleVO;

import java.util.List;

/**
 * 接口说明：角色信息管理业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:10
 */
public interface RoleService {
    boolean save(RoleInsertDTO dto);
    boolean update(RoleUpdateDTO dto);
    Role getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Role> page(RolePageDTO dto);

    List<RoleVO> listAll();
}
