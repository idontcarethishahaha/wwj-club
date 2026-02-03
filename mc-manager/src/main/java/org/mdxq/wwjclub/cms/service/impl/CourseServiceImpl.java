package org.mdxq.wwjclub.cms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Course;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.cms.dao.CourseMapper;
import org.mdxq.wwjclub.cms.dto.CourseInsertDTO;
import org.mdxq.wwjclub.cms.dto.CoursePageDTO;
import org.mdxq.wwjclub.cms.dto.CourseUpdateDTO;
import org.mdxq.wwjclub.cms.service.CourseService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类说明：房间信息管理实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 14:44
 */
@Service
@CacheConfig(cacheNames = "course")//配置Redis缓存名
public class CourseServiceImpl implements CourseService {
    // ctrl +  i 实现所有方法
    //@Autowired 报错
    @Resource//注入一个Mapper依赖
    private CourseMapper courseMapper;

}
