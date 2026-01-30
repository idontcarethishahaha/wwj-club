package org.mdxq.wwjclub.ums.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Emp;
import org.mdxq.wwjclub.ums.dao.EmpMapper;
import org.mdxq.wwjclub.ums.dto.EmpInsertDTO;
import org.mdxq.wwjclub.ums.dto.EmpPageDTO;
import org.mdxq.wwjclub.ums.dto.EmpUpdateDTO;
import org.mdxq.wwjclub.ums.service.EmpService;
import org.springframework.cache.annotation.CacheConfig;
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
    public boolean save(EmpInsertDTO dto) {
        return false;
    }

    @Override
    public boolean update(EmpUpdateDTO dto) {
        return false;
    }

    @Override
    public Emp getById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        return false;
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