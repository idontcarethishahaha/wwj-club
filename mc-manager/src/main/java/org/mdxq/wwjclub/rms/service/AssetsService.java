package org.mdxq.wwjclub.rms.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.constant.MC;
import org.mdxq.wwjclub.entity.Assets;
import org.mdxq.wwjclub.rms.dao.AssetsMapper;
import org.mdxq.wwjclub.rms.dao.RoomMapper;
import org.mdxq.wwjclub.rms.dto.AssetsInsertDTO;
import org.mdxq.wwjclub.rms.dto.AssetsPageDTO;
import org.mdxq.wwjclub.rms.dto.AssetsUpdateDTO;
import org.mdxq.wwjclub.rms.excel.AssetsExcel;
import org.mdxq.wwjclub.util.MinioUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 上传资产图片
     * @param newFile 用户上传的图片文件
     * @param id 资产的id
     * @return 返回图片的文件名
     */
    String uploadPicture(MultipartFile newFile, Long id);

    //获取资产记录的Excel数据
    List<AssetsExcel> getExcelData();
}
