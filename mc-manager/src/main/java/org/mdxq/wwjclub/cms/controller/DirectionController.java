package org.mdxq.wwjclub.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.cms.dto.DirectionInsertDTO;
import org.mdxq.wwjclub.cms.dto.DirectionPageDTO;
import org.mdxq.wwjclub.cms.dto.DirectionUpdateDTO;
import org.mdxq.wwjclub.cms.service.DirectionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：方向管理控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/31 9:09
 */
@RestController
@RequestMapping("/api/v1/direction")
@Tag(name = "方向管理")
public class DirectionController {
    @Resource
    private DirectionService directionService;

    @Operation(summary = "添加 - 单个方向")
    @PostMapping("insert")
    public Result insert(@RequestBody @Validated DirectionInsertDTO dto) {
        return new Result(directionService.save(dto));
    }

    @Operation(summary = "修改 - 单个方向")
    @PutMapping("update")
    public Result update(@RequestBody @Validated DirectionUpdateDTO dto) {
        return new Result(directionService.update(dto));
    }

    @Operation(summary = "删除 - 单个方向")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(directionService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long>ids) {
        return new Result(directionService.deleteBatch(ids));
    }

    @Operation(summary = "查询 - 单个方向")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(directionService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated DirectionPageDTO dto) {
        return new Result(directionService.page(dto));
    }

    @Operation(summary = "查询 - 所有方向")
    @GetMapping("list")
    public Result list() {
        return new Result(directionService.listAll());
    }
}