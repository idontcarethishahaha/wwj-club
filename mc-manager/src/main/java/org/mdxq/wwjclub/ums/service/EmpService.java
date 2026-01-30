package org.mdxq.wwjclub.ums.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.Emp;
import org.mdxq.wwjclub.ums.dto.EmpInsertDTO;
import org.mdxq.wwjclub.ums.dto.EmpPageDTO;
import org.mdxq.wwjclub.ums.dto.EmpUpdateDTO;

import java.util.List;

/**
 * 接口说明：员工信息管理业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 10:21
 */
public interface EmpService {
    boolean save(EmpInsertDTO dto);
    boolean update(EmpUpdateDTO dto);
    Emp getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Emp> page(EmpPageDTO dto);
    List<Emp> listAll();
}
