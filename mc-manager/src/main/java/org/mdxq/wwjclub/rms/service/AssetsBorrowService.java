package org.mdxq.wwjclub.rms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.AssetsBorrow;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowInsertDTO;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowPageDTO;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowUpdateDTO;
import org.mdxq.wwjclub.entity.Assets;

import java.util.List;

/**
 * 接口说明：资产申请信息管理业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/28 14:27
 */
public interface AssetsBorrowService {

    boolean save(AssetsBorrowInsertDTO dto);

    boolean update(AssetsBorrowUpdateDTO dto);

    AssetsBorrow getById(Long id);

    boolean deleteById(Long id);

    boolean deleteBatch(List<Long> ids);

    PageInfo<AssetsBorrow> page(AssetsBorrowPageDTO dto);

    // 归还资产
    int returnAssets(Long id);
}
