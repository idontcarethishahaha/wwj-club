package org.mdxq.wwjclub.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Menu;
import org.mdxq.wwjclub.entity.Role;
import org.mdxq.wwjclub.entity.RoleMenu;
import org.mdxq.wwjclub.exception.RepeatRecordException;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.ums.dao.MenuMapper;
import org.mdxq.wwjclub.ums.dao.RoleMapper;
import org.mdxq.wwjclub.ums.dto.*;
import org.mdxq.wwjclub.ums.service.MenuService;
import org.mdxq.wwjclub.ums.service.RoleService;
import org.mdxq.wwjclub.ums.vo.MenuVO;
import org.mdxq.wwjclub.ums.vo.RoleVO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 类说明：菜单业务实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:18
 */
@Service
@CacheConfig(cacheNames = "menu")//配置Redis缓存名
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuVO> listByRoleId(Long roleId) {
        return menuMapper.listByRoleId(roleId);
    }

    @Override
    public List<MenuVO> listByEmpId(Long empId) {
        return menuMapper.listByEmpId(empId);
    }

    @Override
    @Transactional
    public boolean updateByRoleId(Long roleId, String menuIds) {
        // 先清空该角色已关联的所有菜单
        menuMapper.deleteByRoleId(roleId);
        List<RoleMenu> roleMenus = new ArrayList<>();
        // 使用TreeSet自动去重并按数字升序排序
        TreeSet<Long> menuIdSet = new TreeSet<>();
        for (String menuId : menuIds.split(",")) {
            // 排除空字符串，避免转换异常
            if (StrUtil.isNotBlank(menuId)) {
                menuIdSet.add(Long.parseLong(menuId));
            }
        }
        // 将去重排序后的菜单ID封装为RoleMenu对象
        menuIdSet.forEach(menuId -> {
            roleMenus.add(new RoleMenu(roleId, menuId));
        });
        if(CollUtil.isNotEmpty(roleMenus)) {
            // 批量插入角色-菜单关联关系
            menuMapper.batchInsertRoleMenu(roleMenus);
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean save(MenuInsertDTO dto) {
        Menu menu = BeanUtil.copyProperties(dto, Menu.class);
        if (StrUtil.isEmpty(dto.getInfo())) {
            menu.setInfo("暂无介绍");
        }
        if (menuMapper.insert(menu) == 0) {
            throw new ServerErrorException("DB: 菜单插入失败");
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    @Retryable(retryFor = VersionException.class)
    public boolean update(MenuUpdateDTO dto) {
        Menu menu = menuMapper.selectById(dto.getId());
        if (menu == null) {
            throw new ServerErrorException("DB: 记录不存在或已删除");
        }
        BeanUtil.copyProperties(dto, menu);
        if (menuMapper.update(menu) == 0) {
            throw new VersionException("DB: 菜单更新失败");
        }
        return true;
    }

    @Override
    @Cacheable(key = "#p0", condition = "#result != null")
    public Menu getById(Long id) {
        Menu menu = menuMapper.selectById(id);
        if (menu == null) {
            throw new ServerErrorException("DB: 记录不存在或已删除");
        }
        return menu;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteById(Long id) {
        if (menuMapper.delete(id) == 0) {
            throw new ServerErrorException("DB: 删除菜单失败");
        }
        return true;
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public boolean deleteBatch(List<Long> ids) {
        if (menuMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 批量删除菜单失败");
        }
        return true;
    }

    @Override
    public PageInfo<Menu> page(MenuPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(menuMapper.list(dto));
    }

    @Override
    public List<MenuVO> listAll() {
        return menuMapper.listAll();
    }
}
