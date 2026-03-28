package org.mdxq.wwjclub.rms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.*;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.constant.MC;
import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.entity.Assets;
import org.mdxq.wwjclub.exception.*;
import org.mdxq.wwjclub.rms.dao.AssetsMapper;
import org.mdxq.wwjclub.rms.dto.*;
import org.mdxq.wwjclub.rms.excel.AssetsExcel;
import org.mdxq.wwjclub.rms.service.AssetsService;
import org.mdxq.wwjclub.util.MinioUtil;
import org.springframework.cache.annotation.*;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类说明：资产接口实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 14:44
 */
@Service
@CacheConfig(cacheNames = "assets")//配置Redis缓存名
public class AssetsServiceImpl implements AssetsService {
    // ctrl +  i 实现所有方法
    @Resource
    private AssetsMapper assetsMapper;

    @Override
    @CacheEvict(allEntries = true)
    public boolean save(AssetsInsertDTO dto) {
        String info = dto.getInfo();
        Assets assets = BeanUtil.copyProperties(dto, Assets.class);
        // 设置资产默认图片
        assets.setPicture(MC.Assets.DEFAULT_ASSETS_PIC);
        // 总库存等于初始库存
        assets.setTotal(assets.getStock());
        if (ObjectUtil.isEmpty(info))
            assets.setInfo("暂无介绍");
        if (assetsMapper.insert(assets) == 0) {
            throw new ServerErrorException("DB：添加资产失败");
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional
    @Retryable(retryFor = VersionException.class)
    public boolean update(AssetsUpdateDTO dto) {
        Assets assets = assetsMapper.selectById(dto.getId());
        if(assets == null){
            throw new ServerErrorException("DB: 该资产不存在或已删除");
        }
        BeanUtil.copyProperties(dto, assets);//从dto中复制属性新值,覆盖资产属性
        if(assetsMapper.update(assets) == 0) {
            throw new VersionException("DB: 资产更新失败");
        }
        return true;
    }

    // 查询成功加缓存 cacheable
    @Override
    @Cacheable(key = "#p0",condition = "#p0 != null",unless="#result==null")
    public Assets getById(Long id) {
        Assets assets = assetsMapper.selectById(id);
        if(assets == null) throw new ServerErrorException("DB: 记录不存在");
        return assets;
    }

    @Override
    @CacheEvict(key = "#p0",condition = "#result==true")
    public boolean deleteById(Long id) {
        if(assetsMapper.delete(id) == 0)
            throw new ServerErrorException("删除记录失败");
        return true;
    }

    @Override
    @Transactional//事务注解保证批量删除操作要么都成功，要么都失败
    @CacheEvict(allEntries = true)
    public boolean deleteBatch(List<Long> ids) {
        if(assetsMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 批量删除失败，所有记录将被回滚");
        }
        return true;
    }

    @Override
    public PageInfo<Assets> page(AssetsPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(assetsMapper.list(dto));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public String uploadPicture(MultipartFile newFile, Long id) {
        Assets assets = assetsMapper.selectById(id);
        if (ObjectUtil.isNull(assets)) {
            throw new ServerErrorException("DB: 该资产不存在");
        }

        String oldName = assets.getPicture();//取出资产图片的旧文件名
        String newFilename = MinioUtil.randomFilename(newFile);//得到一个随机的新文件名
        assets.setPicture(newFilename);//设置资产的新图片文件名

        // 更新数据库
        if (assetsMapper.update(assets) == 0) {
            throw new ServerErrorException("DB: 记录更新失败");
        }

        try {
            // 删除旧文件（可选）
            if (!MC.Assets.DEFAULT_ASSETS_PIC.equals(oldName)) {
                MinioUtil.delete(oldName, MC.MinIO.ASSETS_DIR, MC.MinIO.BUCKET_NAME);
            }
            // 上传新文件到 Minio：新文件，文件名，上传路径，桶名称
            MinioUtil.upload(newFile, newFilename, MC.MinIO.ASSETS_DIR, MC.MinIO.BUCKET_NAME);
        } catch (Exception ex) {
            throw new ServerErrorException("Minio操作失败: " + ex.getMessage());
        }

        return newFilename;//如果所有操作都成功，返回新的文件名
    }

    @Override
    public List<AssetsExcel> getExcelData() {
        return assetsMapper.list(new AssetsPageDTO())
                .stream()
                .map(assets -> BeanUtil.copyProperties(assets,AssetsExcel.class))
                .collect(Collectors.toList());
    }
}
