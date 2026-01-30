package org.mdxq.wwjclub.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.*;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Role;
import org.mdxq.wwjclub.exception.*;
import org.mdxq.wwjclub.ums.dao.RoleMapper;
import org.mdxq.wwjclub.ums.dto.*;
import org.mdxq.wwjclub.ums.service.RoleService;
import org.springframework.cache.annotation.*;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 类说明：角色信息管理实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:18
 */
@Service
@CacheConfig(cacheNames = "role")//配置Redis缓存名
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    @CacheEvict(allEntries = true)//插入新值后缓存失效
    public boolean save(RoleInsertDTO dto) {
        String info = dto.getInfo();
        // 将dto中的属性拷贝，得到一个 Role 对象
        Role role = BeanUtil.copyProperties(dto, Role.class);
        // 设置介绍的默认值
        if(ObjectUtil.isEmpty(info)) role.setInfo("暂无介绍");
        if (roleMapper.insert(role) == 0) {
            throw new ServerErrorException("DB: 添加角色失败");
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional
    @Retryable(retryFor = VersionException.class)
    public boolean update(RoleUpdateDTO dto) {
        Role role = roleMapper.selectById(dto.getId());
        if(ObjectUtil.isNull(role)){
            throw new ServerErrorException("DB: 该角色不存在或已删除");
        }
        BeanUtil.copyProperties(dto, role);//从dto中复制属性新值
        if(roleMapper.update(role) == 0) {
            throw new VersionException("DB: 更新角色失败");
        }
        return false;
    }

    @Override
    @Cacheable(key = "#p0",condition = "#p0 != null",unless="#result==null")
    public Role getById(Long id) {
        Role role = roleMapper.selectById(id);
        if(role == null) throw new ServerErrorException("DB: 记录不存在");
        return role;
    }

    @Override
    @CacheEvict(key = "#p0",condition = "#result==true")
    public boolean deleteById(Long id) {
        if(roleMapper.delete(id) == 0)
            throw new ServerErrorException("删除记录失败");
        return true;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true,condition = "result==true")
    public boolean deleteBatch(List<Long> ids) {
        if(roleMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 批量删除失败，所有记录将被回滚");
        }
        return true;
    }

    @Override
    public PageInfo<Role> page(RolePageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(roleMapper.list(dto));
    }
}
