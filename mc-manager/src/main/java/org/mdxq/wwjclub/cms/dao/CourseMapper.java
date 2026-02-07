package org.mdxq.wwjclub.cms.dao;

import org.mdxq.wwjclub.cms.vo.CourseVO;
import org.mdxq.wwjclub.entity.Course;
import org.mdxq.wwjclub.mapper.BaseMapper;

import java.util.List;

/**
 * 接口说明：课程Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/3 11:49
 */
public interface CourseMapper extends BaseMapper<Course> {
    List<CourseVO> listAll();
}
