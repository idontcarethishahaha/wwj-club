package org.mdxq.wwjclub.ums.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.ums.dto.*;
import org.mdxq.wwjclub.ums.service.EmpService;
import org.mdxq.wwjclub.ums.service.EmpService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Operation(summary = "修改 - 员工密码")
    @PutMapping("updatePassword")
    public Result updatePassword(@RequestBody @Validated UpdatePasswordDTO dto) {
        return new Result(empService.updatePassword(dto));
    }


    @Operation(summary = "上传 - 员工头像")
    @PostMapping("uploadAvatar/{id}")
    public Result uploadAvatar(@RequestParam("avatarFile") MultipartFile avatarFile, @PathVariable("id") Long id) {
        return new Result(empService.uploadAvatar(avatarFile, id));
    }

    @PostMapping("loginByAccount")
    @Operation(summary = "登录 - 账号密码")
    public Result loginByAccount(@RequestBody @Validated LoginDTO dto) {
        return new Result(empService.login(dto));
    }
}
