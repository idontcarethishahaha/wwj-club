package org.mdxq.wwjclub.cms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.cms.vo.CourseVO;
import org.mdxq.wwjclub.cms.vo.DirectionVO;
import org.mdxq.wwjclub.entity.Course;
import org.mdxq.wwjclub.cms.dto.CourseInsertDTO;
import org.mdxq.wwjclub.cms.dto.CoursePageDTO;
import org.mdxq.wwjclub.cms.dto.CourseUpdateDTO;

import java.util.List;

/**
 * 接口说明：课程信息管理业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/3 11:47
 */

public interface CourseService {
    boolean save(CourseInsertDTO dto);
    boolean update(CourseUpdateDTO dto);
    Course getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Course> page(CoursePageDTO dto);

    List<CourseVO> listAll();
}

