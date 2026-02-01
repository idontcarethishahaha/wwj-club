package org.mdxq.wwjclub.ums.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.Menu;
import org.mdxq.wwjclub.entity.Menu;
import org.mdxq.wwjclub.mapper.BaseMapper;
import org.mdxq.wwjclub.ums.vo.MenuVO;

import java.util.List;

/**
 * 接口说明：菜单Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 9:31
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu>{

    List<MenuVO> listAll();

//    @Override
//    int insert(Menu role);
//
//    @Override
//    int update(Menu role);
//
//    @Override
//    int delete(Long id);
//
//    @Override
//    int deleteBatch(List<Long> ids);
//
//    @Override
//    Menu selectById(Long id);
//
//    @Override
//    List<Menu> list(PageDTO pageDTO);
    
}
