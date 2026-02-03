package org.mdxq.wwjclub.ums.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.Emp;
import org.mdxq.wwjclub.ums.dto.*;
import org.mdxq.wwjclub.ums.vo.LoginVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 接口说明：员工业务接口
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

    String uploadAvatar(MultipartFile newFile, Long id);

    boolean updatePassword(UpdatePasswordDTO dto);

    LoginVO login(LoginDTO loginDTO);
}
