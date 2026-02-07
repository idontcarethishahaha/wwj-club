package org.mdxq.wwjclub.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.dto.ClubProgressInsertDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressPageDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressUpdateDTO;
import org.mdxq.wwjclub.cms.service.ClubProgressService;
import org.mdxq.wwjclub.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：班级进度控制器
 *
 * @author WuWenJin
 * @date 2026/2/5 15:57
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/clubProgress")
@Tag(name = "班级进度控制器")
public class ClubProgressController {
    @Resource
    private ClubProgressService progressService;

    @PostMapping("insert")
    @Operation(summary = "添加 - 单个进度")
    public Result insert(@RequestBody @Validated ClubProgressInsertDTO dto) {
        return new Result(progressService.save(dto));
    }

    @PutMapping("update")
    @Operation(summary = "修改 - 单个进度")
    public Result update(@RequestBody @Validated ClubProgressUpdateDTO dto) {
        return new Result(progressService.update(dto));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除 - 单个进度")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(progressService.deleteById(id));
    }

    @DeleteMapping("deleteBatch")
    @Operation(summary = "删除 - 批量删除")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(progressService.deleteBatch(ids));
    }

    @GetMapping("select/{id}")
    @Operation(summary = "查询 - 单个进度")
    public Result select(@PathVariable("id") Long id) {
        return new Result(progressService.getById(id));
    }

    @GetMapping("page")
    @Operation(summary = "查询 - 分页查询")
    public Result page(ClubProgressPageDTO dto) {
        return new Result(progressService.page(dto));
    }
}
