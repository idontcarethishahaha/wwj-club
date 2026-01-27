package org.mdxq.wwjclub.rms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.Assets;
import org.mdxq.wwjclub.rms.dto.AssetsInsertDTO;
import org.mdxq.wwjclub.rms.dto.AssetsPageDTO;
import org.mdxq.wwjclub.rms.dto.AssetsUpdateDTO;

import java.util.List;

/**
 * 接口说明：资产信息管理业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 14:36
 */
// ctrl + R
public interface AssetsService {
    boolean save(AssetsInsertDTO dto);
    boolean update(AssetsUpdateDTO dto);
    Assets getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Assets> page(AssetsPageDTO dto);
}
