package org.mdxq.wwjclub.rms.dao;

import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.Assets;
import org.mdxq.wwjclub.entity.AssetsBorrow;
import org.mdxq.wwjclub.mapper.BaseMapper;

import java.util.List;

/**
 * 接口说明：Mapper接口模板
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 9:31
 */
public interface AssetsBorrowMapper extends BaseMapper<AssetsBorrow> {
    // ctrl + O
    @Override
    int insert(AssetsBorrow assetsBorrow);

    @Override
    List<AssetsBorrow> list(PageDTO pageDTO);

    @Override
    int update(AssetsBorrow assetsBorrow);

    @Override
    int delete(Long id);

    @Override
    int deleteBatch(List<Long> ids);

    @Override
    AssetsBorrow selectById(Long id);
}
