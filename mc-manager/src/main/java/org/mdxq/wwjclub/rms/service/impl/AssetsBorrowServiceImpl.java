package org.mdxq.wwjclub.rms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.entity.Assets;
import org.mdxq.wwjclub.entity.AssetsBorrow;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.rms.dao.AssetsBorrowMapper;
import org.mdxq.wwjclub.rms.dao.AssetsMapper;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowInsertDTO;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowPageDTO;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowUpdateDTO;
import org.mdxq.wwjclub.rms.service.AssetsBorrowService;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类说明：资产申请业务实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/28 14:27
 */
@Service
//CacheConfig(cacheNames="assets")
public class AssetsBorrowServiceImpl implements AssetsBorrowService {

    @Resource
    private AssetsBorrowMapper borrowMapper;
    @Resource
    private AssetsMapper assetsMapper;

    @Override
    @Transactional
    public boolean save(AssetsBorrowInsertDTO dto) {
        AssetsBorrow assetsBorrow = BeanUtil.copyProperties(dto, AssetsBorrow.class);
        // 如果申请时间晚于预计归还时间，报错
        if (dto.getBorrowTime().isAfter(dto.getExpectedReturnTime())) {
            throw new ServerErrorException("申请时间不能晚于预计归还时间");
        }
        // 根据资产ID查询资产
        Assets assets = assetsMapper.selectById(dto.getFkAssetsId());
        if (assets == null) {
            throw new ServerErrorException("申请的资产不存在");
        }
        // 如果申请数量超过资产的库存，抛出异常
        if (dto.getCount() > assets.getStock()) {
            throw new ServerErrorException("申请数量不能超出库存: " + assets.getStock());
        }
        if (ObjectUtil.isEmpty(dto.getInfo())) {
            assetsBorrow.setInfo("暂无");
        }
        if (borrowMapper.insert(assetsBorrow) == 0) {
            throw new ServerErrorException("DB: 添加资产申请失败");
        }
        // 如果申请成功，就从资产表中扣除 count 数量的库存
        assets.setStock(assets.getStock() - dto.getCount());
        assetsMapper.update(assets);

        return true;
    }

    @Override
    @Transactional
    @Retryable(retryFor = VersionException.class)
    public boolean update(AssetsBorrowUpdateDTO dto) {
        Assets assets = assetsMapper.selectById(dto.getFkAssetsId());
        if (assets == null) {
            throw new ServerErrorException("DB: 该资产不存在或已删除");
        }

        AssetsBorrow assetsBorrow = borrowMapper.selectById(dto.getId());
        int stock = assets.getStock();
        stock -= (dto.getCount()-assetsBorrow.getCount());
        if(stock<0){
            throw new ServerErrorException("申请数量不能炒出库存："+assets.getStock());
        }
        assets.setStock(stock);//更新库存
        assetsMapper.update(assets);//修改数据库
        if (assetsBorrow == null) {
            throw new ServerErrorException("记录不存在");
        }

        BeanUtil.copyProperties(dto, assetsBorrow);

        if (borrowMapper.update(assetsBorrow) == 0) {
            throw new VersionException("DB: 记录更新失败");
        }

        return true;
    }

    @Override
    public AssetsBorrow getById(Long id) {
        AssetsBorrow assetsBorrow = borrowMapper.selectById(id);
        if (assetsBorrow == null) {
            throw new ServerErrorException("记录不存在");
        }
        return assetsBorrow;
    }

    @Override
    public boolean deleteById(Long id) {
        if (borrowMapper.delete(id) == 0) {
            throw new ServerErrorException("DB: 记录删除失败");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBatch(List<Long> ids) {
        if (borrowMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 记录批量删除失败");
        }
        return true;
    }

    @Override
    public PageInfo<AssetsBorrow> page(AssetsBorrowPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(borrowMapper.list(dto));
    }

    @Override
    @Transactional//保证n1和n2两个操作原子性，要么都执行，要么都不执行
    public int returnAssets(Long id) {
        AssetsBorrow assetsBorrow = borrowMapper.selectById(id);
        if (assetsBorrow == null) {
            throw new ServerErrorException("DB: 申请记录不存在");
        }
        // 查找资产记录
        Assets assets = assetsMapper.selectById(assetsBorrow.getFkAssetsId());
        if(assets==null){
            throw new ServerErrorException("DB: 申请记录不存在或已删除");
        }
        assets.setStock(assets.getStock() + assetsBorrow.getCount());//恢复资产库存
        int n1 = assetsMapper.update(assets);
        if(n1==0){
            throw new ServerErrorException("DB: 修改库存失败");
        }
        //设置归还时间
        assetsBorrow.setReturnTime(LocalDateTime.now());
        int n2  = borrowMapper.update(assetsBorrow);
        if(n2==0){
            throw new ServerErrorException("DB: 修改数据库失败");
        }
        return 1;
    }
}
