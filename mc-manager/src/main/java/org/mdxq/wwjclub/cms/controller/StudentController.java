package org.mdxq.wwjclub.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.mdxq.wwjclub.cms.dto.StudentInsertDTO;
import org.mdxq.wwjclub.cms.dto.StudentPageDTO;
import org.mdxq.wwjclub.cms.dto.StudentUpdateDTO;
import org.mdxq.wwjclub.cms.service.StudentService;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.util.ExcelUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 类说明：学生控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/4 15:59
 */
@RestController
@RequestMapping("/api/v1/student")
@Tag(name = "学生管理")
public class StudentController {
    @Resource
    private StudentService studentService;

    @Operation(summary = "添加 - 单个学生")
    @PostMapping("insert")
    public Result insert(@RequestBody @Validated StudentInsertDTO dto) {
        return new Result(studentService.save(dto));
    }

    @Operation(summary = "修改 - 单个学生")
    @PutMapping("update")
    public Result update(@RequestBody @Validated StudentUpdateDTO dto) {
        return new Result(studentService.update(dto));
    }

    @Operation(summary = "删除 - 单个学生")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(studentService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(studentService.deleteBatch(ids));
    }

    @Operation(summary = "查询 - 单个学生")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(studentService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated StudentPageDTO dto) {
        return new Result(studentService.page(dto));
    }

    @Operation(summary = "上传 - 学生头像")
    @PostMapping("uploadAvatar/{id}")
    public Result uploadAvatar(@RequestParam("avatarFile") MultipartFile avatarFile, @PathVariable("id") Long id) {
        return new Result(studentService.uploadAvatar(avatarFile, id));
    }

    @Operation(summary = "查询 - 导出数据表")
    @GetMapping("/excel")
    public void excel(HttpServletResponse response) {
        ExcelUtil.download(response,"学生统计表",studentService.getExcelData());
    }
}
