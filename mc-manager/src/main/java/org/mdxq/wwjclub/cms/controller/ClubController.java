package org.mdxq.wwjclub.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.dto.ClubInsertDTO;
import org.mdxq.wwjclub.cms.dto.ClubPageDTO;
import org.mdxq.wwjclub.cms.dto.ClubProgressUpdateDTO;
import org.mdxq.wwjclub.cms.dto.ClubUpdateDTO;
import org.mdxq.wwjclub.cms.service.ClubService;
import org.mdxq.wwjclub.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类说明：班级管理控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/31 9:09
 */
@RestController
@RequestMapping("/api/v1/club")
@Tag(name = "班级管理")
public class ClubController {
    @Resource
    private ClubService clubService;

    @Operation(summary = "添加 - 单个班级")
    @PostMapping("insert")
    public Result insert(@RequestBody @Validated ClubInsertDTO dto) {
        return new Result(clubService.save(dto));
    }

    @Operation(summary = "修改 - 单个班级")
    @PutMapping("update")
    public Result update(@RequestBody @Validated ClubUpdateDTO dto) {
        return new Result(clubService.update(dto));
    }

    @Operation(summary = "删除 - 单个班级")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(clubService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long>ids) {
        return new Result(clubService.deleteBatch(ids));
    }

    @Operation(summary = "查询 - 单个班级")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(clubService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated ClubPageDTO dto) {
        return new Result(clubService.page(dto));
    }

    @Operation(summary = "查询 - 所有班级")
    @GetMapping("list")
    public Result list() {
        return new Result(clubService.listAll());
    }

    @PutMapping("finish/{id}")
    @Operation(summary = "修改 - 班级结课时间")
    public Result finish(@PathVariable("id") Long id) {
        ClubUpdateDTO dto = new ClubUpdateDTO();
        dto.setId(id);
        dto.setEndTime(LocalDateTime.now());
        return new Result(clubService.update(dto));
    }
}