package org.mdxq.wwjclub.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Dept;
import org.mdxq.wwjclub.exception.RepeatRecordException;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.ums.dao.DeptMapper;
import org.mdxq.wwjclub.ums.dto.DeptInsertDTO;
import org.mdxq.wwjclub.ums.dto.DeptPageDTO;
import org.mdxq.wwjclub.ums.dto.DeptUpdateDTO;
import org.mdxq.wwjclub.ums.service.DeptService;
import org.mdxq.wwjclub.ums.vo.DeptVO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类说明：部门信息管理实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:18
 */
@Service
@CacheConfig(cacheNames = "dept")//配置Redis缓存名
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    @CacheEvict(allEntries = true)
    public boolean save(DeptInsertDTO dto) {
        // 验证部门名称是否重复
        if (deptMapper.countByTitle(dto.getTitle()) > 0) {
            throw new RepeatRecordException("部门名称重复");
        }
        Dept dept = BeanUtil.copyProperties(dto, Dept.class);
        if (ObjectUtil.isEmpty(dto.getInfo())) {
            dept.setInfo("暂无介绍");
        }
        if (deptMapper.insert(dept) == 0) {
            throw new ServerErrorException("DB: 添加部门失败");
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean update(DeptUpdateDTO dto) {
        Dept dept = deptMapper.selectById(dto.getId());
        if (dept == null) {
            throw new ServerErrorException("DB: 记录不存在或已删除");
        }
        BeanUtil.copyProperties(dto, dept);
        if (deptMapper.update(dept) == 0) {
            throw new ServerErrorException("DB: 更新部门失败");
        }
        return true;
    }

    @Override
    @Cacheable(key = "#p0", condition = "#result != null")
    public Dept getById(Long id) {
        Dept dept = deptMapper.selectById(id);
        if (dept == null) {
            throw new ServerErrorException("DB: 记录不存在或已删除");
        }
        return dept;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteById(Long id) {
        if (deptMapper.delete(id) == 0) {
            throw new ServerErrorException("DB: 删除部门失败");
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteBatch(List<Long> ids) {
        if (deptMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 删除部门失败");
        }
        return true;
    }

    @Override
    public PageInfo<Dept> page(DeptPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(deptMapper.list(dto));
    }

    @Override
    public List<DeptVO> listAll() {
        return deptMapper.listAll();
    }
}
