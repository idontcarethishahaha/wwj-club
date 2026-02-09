package org.mdxq.wwjclub.ums.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.rms.dto.AssetsInsertDTO;
import org.mdxq.wwjclub.rms.dto.AssetsPageDTO;
import org.mdxq.wwjclub.rms.dto.AssetsUpdateDTO;
import org.mdxq.wwjclub.rms.service.AssetsService;
import org.mdxq.wwjclub.ums.dto.RoleInsertDTO;
import org.mdxq.wwjclub.ums.dto.RolePageDTO;
import org.mdxq.wwjclub.ums.dto.RoleUpdateDTO;
import org.mdxq.wwjclub.ums.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：角色管理控制层
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:19
 */
@Tag(name = "角色管理模块")
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @Operation(summary = "新增 - 单条记录")
    @PostMapping("insert")
    public Result save(@RequestBody @Validated RoleInsertDTO dto) {
        return new Result(roleService.save(dto));
    }

    @Operation(summary = "查询 - 单条记录")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(roleService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated RolePageDTO dto) {
        return new Result(roleService.page(dto));
    }

    @Operation(summary = "更新 - 单条记录")
    @PutMapping("update")
    public Result update(@RequestBody @Validated RoleUpdateDTO dto) {
        return new Result(roleService.update(dto));
    }

    @Operation(summary = "删除 - 单条记录")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(roleService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量操作")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(roleService.deleteBatch(ids));
    }

    @GetMapping("list")
    @Operation(summary = "查询 - 所有角色")
    public Result list() {
        return new Result(roleService.listAll());
    }

    @GetMapping("listByEmpId/{empId}")
    @Operation(summary = "查询 - 某个员工拥有的角色")
    public Result listByEmpId(@PathVariable("empId") Long empId) {
        return new Result(roleService.listByEmpId(empId));
    }

//    @PutMapping("updateByEmpId")
//    @Operation(summary = "修改 - 员工的角色")
//    public Result listByEmpId(@PathVariable("empId") Long empId,@RequestParam("roleIds") List<Long> roleIds) {
//        return new Result(roleService.updateByEmpId(empId, roleIds));
//    }
@PutMapping ("updateByEmpId")
@Operation (summary = "修改 - 员工的角色")
public Result updateByEmpId (@RequestParam ("empId") Long empId, @RequestParam ("roleIds") List<Long> roleIds)
{return new Result(roleService.updateByEmpId(empId, roleIds));}

}


