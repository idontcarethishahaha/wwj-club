package org.mdxq.wwjclub.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Menu;
import org.mdxq.wwjclub.entity.Role;
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

import java.util.List;

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
