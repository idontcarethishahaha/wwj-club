package org.mdxq.wwjclub.cms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.cms.dto.StudentInsertDTO;
import org.mdxq.wwjclub.cms.dto.StudentPageDTO;
import org.mdxq.wwjclub.cms.dto.StudentUpdateDTO;
import org.mdxq.wwjclub.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 类说明：班级业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/5 10:18
 */
public interface StudentService {
    boolean save(StudentInsertDTO dto);
    boolean update(StudentUpdateDTO dto);
    Student getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Student> page(StudentPageDTO dto);

    String uploadAvatar(MultipartFile avatarFile,Long id);
}