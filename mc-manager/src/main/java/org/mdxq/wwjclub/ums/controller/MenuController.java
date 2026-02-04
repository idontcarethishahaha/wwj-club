package org.mdxq.wwjclub.ums.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.ums.dto.MenuInsertDTO;
import org.mdxq.wwjclub.ums.dto.MenuPageDTO;
import org.mdxq.wwjclub.ums.dto.MenuUpdateDTO;
import org.mdxq.wwjclub.ums.service.MenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：菜单管理控制层
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:19
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @Operation(summary = "新增 - 单条记录")
    @PostMapping("insert")
    public Result save(@RequestBody @Validated MenuInsertDTO dto) {
        return new Result(menuService.save(dto));
    }

    @Operation(summary = "查询 - 单条记录")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(menuService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated MenuPageDTO dto) {
        return new Result(menuService.page(dto));
    }

    @Operation(summary = "更新 - 单条记录")
    @PutMapping("update")
    public Result update(@RequestBody @Validated MenuUpdateDTO dto) {
        return new Result(menuService.update(dto));
    }

    @Operation(summary = "删除 - 单条记录")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(menuService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量操作")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(menuService.deleteBatch(ids));
    }

    @GetMapping("list")
    @Operation(summary = "查询 - 所有菜单")
    public Result list() {
        return new Result(menuService.listAll());
    }

    @Operation(summary = "查询 - 根据员工ID")
    @GetMapping("listByEmpId/{empId}")
    public Result listByEmpId(@PathVariable("empId") Long empId) {
        return new Result(menuService.listByEmpId(empId));
    }

    @Operation(summary = "查询 - 根据角色ID")
    @GetMapping("listByRoleId/{roleId}")
    public Result listByRoleId(@PathVariable("roleId") Long roleId) {
        return new Result(menuService.listByRoleId(roleId));
    }
}


