package org.mdxq.wwjclub.ums.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.Dept;
import org.mdxq.wwjclub.ums.dto.DeptInsertDTO;
import org.mdxq.wwjclub.ums.dto.DeptPageDTO;
import org.mdxq.wwjclub.ums.dto.DeptUpdateDTO;

import java.util.List;

/**
 * 接口说明：部门信息管理业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 11:39
 */
public interface DeptService {
    boolean save(DeptInsertDTO dto);
    boolean update(DeptUpdateDTO dto);
    Dept getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Dept> page(DeptPageDTO dto);
    List<Dept> listAll();
}
