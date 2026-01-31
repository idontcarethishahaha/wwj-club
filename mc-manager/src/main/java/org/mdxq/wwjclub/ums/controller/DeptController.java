package org.mdxq.wwjclub.ums.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.ums.dto.DeptInsertDTO;
import org.mdxq.wwjclub.ums.dto.DeptPageDTO;
import org.mdxq.wwjclub.ums.dto.DeptUpdateDTO;
import org.mdxq.wwjclub.ums.service.DeptService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：部门管理控制类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 10:22
 */
@Tag(name = "部门管理")
@RestController
@RequestMapping("/api/v1/dept")
public class DeptController {
    @Resource
    private DeptService deptService;

    @Operation(summary = "添加 - 单个部门")
    @PostMapping("insert")
    public Result insert(@RequestBody @Validated DeptInsertDTO dto) {
        return new Result(deptService.save(dto));
    }

    @Operation(summary = "修改 - 单个部门")
    @PutMapping("update")
    public Result update(@RequestBody @Validated DeptUpdateDTO dto) {
        return new Result(deptService.update(dto));
    }

    @Operation(summary = "删除 - 单个部门")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(deptService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量操作")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(deptService.deleteBatch(ids));
    }

    @Operation(summary = "查询 - 单个部门")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(deptService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated DeptPageDTO dto) {
        return new Result(deptService.page(dto));
    }

    @GetMapping("list")
    @Operation(summary = "查询 - 所有部门")
    public Result list() {
        return new Result(deptService.listAll());
    }

}
