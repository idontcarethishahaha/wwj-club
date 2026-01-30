package org.mdxq.wwjclub.ums.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.ums.dto.EmpInsertDTO;
import org.mdxq.wwjclub.ums.dto.EmpPageDTO;
import org.mdxq.wwjclub.ums.dto.EmpUpdateDTO;
import org.mdxq.wwjclub.ums.service.EmpService;
import org.mdxq.wwjclub.ums.service.EmpService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：员工管理控制类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 10:22
 */
@Tag(name = "员工模块")
@RestController
@RequestMapping("/api/v1/emp")
public class EmpController {
    @Resource
    private EmpService empService;

    @GetMapping("list")
    @Operation(description = "查询 - 全部员工")
    public Result list() {
        return new Result(empService.listAll());
    }

    @Operation(summary = "新增 - 单条记录")
    @PostMapping("insert")
    public Result save(@RequestBody @Validated EmpInsertDTO dto) {
        return new Result(empService.save(dto));
    }

    @Operation(summary = "查询 - 单条记录")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(empService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated EmpPageDTO dto) {
        return new Result(empService.page(dto));
    }

    @Operation(summary = "更新 - 单条记录")
    @PutMapping("update")
    public Result update(@RequestBody @Validated EmpUpdateDTO dto) {
        return new Result(empService.update(dto));
    }

    @Operation(summary = "删除 - 单条记录")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(empService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量操作")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(empService.deleteBatch(ids));
    }
}
