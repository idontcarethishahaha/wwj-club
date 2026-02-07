package org.mdxq.wwjclub.cms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.dto.CoursePageDTO;
import org.mdxq.wwjclub.cms.vo.CourseVO;
import org.mdxq.wwjclub.entity.Course;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.cms.dao.CourseMapper;
import org.mdxq.wwjclub.cms.dto.CourseInsertDTO;
import org.mdxq.wwjclub.cms.dto.CourseUpdateDTO;
import org.mdxq.wwjclub.cms.service.CourseService;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明：课程业务实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 14:44
 */
@Service
@CacheConfig(cacheNames = "course")//配置Redis缓存名
public class CourseServiceImpl implements CourseService {
    // ctrl +  i 实现所有方法
    @Resource//注入一个Mapper依赖
    private CourseMapper courseMapper;

    @Override
    public boolean save(CourseInsertDTO dto) {
        Course course = BeanUtil.copyProperties(dto, Course.class);
        if (StrUtil.isEmpty(dto.getInfo())){
            course.setInfo("暂无介绍");
        }
        // important
        BeanUtil.copyProperties(dto, course);
        if(courseMapper.insert(course)==0){
            throw new ServerErrorException("DB: 插入新课程失败");
        }
        return true;
    }

    @Override
    public boolean update(CourseUpdateDTO dto) {
        Course Course = courseMapper.selectById(dto.getId());
        if (Course == null) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        BeanUtil.copyProperties(dto, Course);
        if (courseMapper.update(Course) == 0) {
            throw new VersionException("DB：课程更新失败");
        }
        return true;
    }

    @Override
    public Course getById(Long id) {
        Course Course = courseMapper.selectById(id);
        if (Course == null) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        return Course;
    }

    @Override
    public boolean deleteById(Long id) {
        if (courseMapper.delete(id) == 0) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        return true;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        if (courseMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB：批量删除失败");
        }
        return true;
    }

    @Override
    public PageInfo<Course> page(CoursePageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(courseMapper.list(dto));
    }

    @Override
    public List<CourseVO> listAll() {
        return courseMapper.listAll();
    }
}
