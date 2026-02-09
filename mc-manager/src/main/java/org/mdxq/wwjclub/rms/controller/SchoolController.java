package org.mdxq.wwjclub.rms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.rms.dto.SchoolInsertDTO;
import org.mdxq.wwjclub.rms.dto.SchoolPageDTO;
import org.mdxq.wwjclub.rms.dto.SchoolUpdateDTO;
import org.mdxq.wwjclub.rms.service.SchoolService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：学校管理控制层
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 16:59
 */
@Tag(name = "学校管理模块")
@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    @PostMapping("insert")
    @Operation(summary = "新增 - 单条记录")
    public Result insert(@RequestBody @Validated SchoolInsertDTO dto) {
        return new Result(schoolService.save(dto));
    }

    @PutMapping("update")
    @Operation(summary = "更新 - 单条记录")
    public Result update(@RequestBody @Validated SchoolUpdateDTO dto) {
        return new Result(schoolService.update(dto));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary="删除 - 单条记录")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(schoolService.deleteById(id));
    }

    //   /api/v1/school/deleteBatch?ids=5,6
    @DeleteMapping("deleteBatch")
    @Operation(summary = "删除 - 批量操作")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(schoolService.deleteBatch(ids));
    }

    @GetMapping("select/{id}")
    @Operation(summary = "查询 - 单条记录")
    public Result select(@PathVariable("id") Long id) {
        return new Result(schoolService.getById(id));
    }

    //  /api/v1/school/page?pageNum=1&pageSize=5&title=测试
    @GetMapping("page")
    @Operation(summary = "查询 - 分页")
    public Result page(@Validated SchoolPageDTO dto) {
        return new Result(schoolService.page(dto));
    }

    @Operation(summary = "查询 - 所有学校")
    @GetMapping("list")
    public Result list() {
        return new Result(schoolService.listAll());
    }
}
