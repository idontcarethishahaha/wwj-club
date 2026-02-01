package org.mdxq.wwjclub.ums.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.Menu;
import org.mdxq.wwjclub.ums.dto.MenuInsertDTO;
import org.mdxq.wwjclub.ums.dto.MenuPageDTO;
import org.mdxq.wwjclub.ums.dto.MenuUpdateDTO;
import org.mdxq.wwjclub.ums.vo.MenuVO;

import java.util.List;

/**
 * 接口说明：菜单业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:10
 */
public interface MenuService {
    boolean save(MenuInsertDTO dto);

    boolean update(MenuUpdateDTO dto);

    Menu getById(Long id);

    boolean deleteById(Long id);

    boolean deleteBatch(List<Long> ids);

    PageInfo<Menu> page(MenuPageDTO dto);

    List<MenuVO> listAll();
}