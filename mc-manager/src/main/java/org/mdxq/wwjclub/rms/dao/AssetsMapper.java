package org.mdxq.wwjclub.rms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.Assets;
import org.mdxq.wwjclub.mapper.BaseMapper;

import java.util.List;

/**
 * 接口说明：资产表访问Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/27 14:32
 */
@Mapper
public interface AssetsMapper extends BaseMapper<Assets> {
    // ctrl + o
    @Override
    int insert(Assets assets);

    @Override
    int update(Assets assets);

    @Override
    int delete(Long id);

    @Override
    int deleteBatch(List<Long> ids);

    @Override
    Assets selectById(Long id);

    @Override
    List<Assets> list(PageDTO pageDTO);
}
