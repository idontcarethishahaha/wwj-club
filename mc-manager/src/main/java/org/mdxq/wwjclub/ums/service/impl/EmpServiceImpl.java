package org.mdxq.wwjclub.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Emp;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.ums.dao.EmpMapper;
import org.mdxq.wwjclub.ums.dto.EmpInsertDTO;
import org.mdxq.wwjclub.ums.dto.EmpPageDTO;
import org.mdxq.wwjclub.ums.dto.EmpUpdateDTO;
import org.mdxq.wwjclub.ums.service.EmpService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明：员工业务接口实现类
 *
 * @author WuWenJin
 * @date 2026/1/30 10:32
 * @version 1.0
 */
@Service
@CacheConfig(cacheNames = "emp")//配置Redis缓存名
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;


    @Override
    @CacheEvict(allEntries = true) // 新增员工后清除所有emp缓存，保证缓存一致性
    public boolean save(EmpInsertDTO dto) {
        // 1. 转换DTO为实体类
        Emp emp = BeanUtil.copyProperties(dto, Emp.class);

        // 2. 可选：为非必填且可能为空的字段设置默认值（参考DeptServiceImpl的风格）
        if (ObjectUtil.isEmpty(emp.getInfo())) {
            emp.setInfo("暂无员工描述");
        }
        // 可根据业务需求添加其他字段的默认值设置，例如性别、年龄等

        // 3. 调用Mapper执行插入操作
        if (empMapper.insert(emp) == 0) {
            throw new ServerErrorException("DB: 添加员工失败");
        }

        return true;
    }

    @Override
    @CacheEvict(allEntries = true) // 更新员工后清除所有emp缓存，保证缓存一致性
    public boolean update(EmpUpdateDTO dto) {
        // 1. 先查询待更新的员工是否存在（未被逻辑删除）
        Emp emp = empMapper.selectById(dto.getId());
        if (emp == null) {
            throw new ServerErrorException("DB: 员工记录不存在或已删除");
        }

        // 2. 拷贝DTO中的更新字段到实体类
        BeanUtil.copyProperties(dto, emp);

        // 3. 调用Mapper执行更新操作
        if (empMapper.update(emp) == 0) {
            throw new ServerErrorException("DB: 更新员工失败");
        }

        return true;
    }

    @Override
    @Cacheable(key = "#p0", condition = "#result != null") // 根据员工id缓存查询结果
    public Emp getById(Long id) {
        // 1. 调用Mapper查询员工
        Emp emp = empMapper.selectById(id);

        // 2. 校验查询结果，不存在则抛出异常
        if (emp == null) {
            throw new ServerErrorException("DB: 员工记录不存在或已删除");
        }

        return emp;
    }

    @Override
    @CacheEvict(allEntries = true) // 删除员工后清除所有emp缓存，保证缓存一致性
    public boolean deleteById(Long id) {
        // 1. 调用Mapper执行逻辑删除
        if (empMapper.delete(id) == 0) {
            throw new ServerErrorException("DB: 删除员工失败");
        }

        return true;
    }

    @Override
    @CacheEvict(allEntries = true) // 批量删除员工后清除所有emp缓存，保证缓存一致性
    public boolean deleteBatch(List<Long> ids) {
        // 1. 校验入参是否为空（可选，增加代码健壮性）
        if (ObjectUtil.isEmpty(ids)) {
            throw new ServerErrorException("请选择需要删除的员工");
        }

        // 2. 调用Mapper执行批量逻辑删除，校验影响结果
        if (empMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 批量删除员工失败");
        }

        return true;
    }


    @Override
    public PageInfo<Emp> page(EmpPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(empMapper.list(dto));
    }

    @Override
    public List<Emp> listAll() {
        EmpPageDTO dto = new EmpPageDTO();
        dto.setPageNum(1);
        dto.setPageSize(Integer.MAX_VALUE);
        return empMapper.list(dto);
    }
}