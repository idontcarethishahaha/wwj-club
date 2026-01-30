package org.mdxq.wwjclub.rms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowInsertDTO;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowPageDTO;
import org.mdxq.wwjclub.rms.dto.AssetsBorrowUpdateDTO;
import org.mdxq.wwjclub.rms.dto.AssetsPageDTO;
import org.mdxq.wwjclub.rms.service.AssetsBorrowService;
import org.mdxq.wwjclub.rms.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：资产申请管理控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 15:42
 *
 * http://localhost:23101/doc.html#/home
 */
@Tag(name = "资产申请管理")
@RestController
@RequestMapping("/api/v1/assetsBorrow")
public class AssetsBorrowController {
    @Resource
    private AssetsBorrowService assetsBorrowService;
    @Resource
    private AssetsService assetsService;

    @Operation(summary = "更新 - 归还资产")
    @PutMapping("returnAssets/{id}")
    public Result returnAssets(@PathVariable("id") Long id){
        return new Result(assetsBorrowService.returnAssets(id));
    }

    @Operation(summary = "新增 - 单条记录")
    @PostMapping("insert")
    public Result save(@RequestBody @Validated AssetsBorrowInsertDTO dto) {
        return new Result(assetsBorrowService.save(dto));
    }

    @Operation(summary = "查询 - 单条记录")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(assetsBorrowService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated AssetsBorrowPageDTO dto) {
        return new Result(assetsBorrowService.page(dto));
    }

    @Operation(summary = "更新 - 单条记录")
    @PutMapping("update")
    public Result update(@RequestBody @Validated AssetsBorrowUpdateDTO dto) {
        return new Result(assetsBorrowService.update(dto));
    }

    @Operation(summary = "删除 - 单条记录")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(assetsBorrowService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量操作")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(assetsBorrowService.deleteBatch(ids));
    }
}
