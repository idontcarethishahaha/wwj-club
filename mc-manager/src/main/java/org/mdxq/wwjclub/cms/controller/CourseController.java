package org.mdxq.wwjclub.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.dto.CourseInsertDTO;
import org.mdxq.wwjclub.cms.dto.CoursePageDTO;
import org.mdxq.wwjclub.cms.dto.CourseUpdateDTO;
import org.mdxq.wwjclub.cms.service.CourseService;
import org.mdxq.wwjclub.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：课程管理控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/4 15:59
 */
@RestController
@RequestMapping("/api/v1/course")
@Tag(name = "课程管理")
public class CourseController {
    @Resource
    private CourseService courseService;

    @Operation(summary = "添加 - 单个课程")
    @PostMapping("insert")
    public Result insert(@RequestBody @Validated CourseInsertDTO dto) {
        return new Result(courseService.save(dto));
    }

    @Operation(summary = "修改 - 单个课程")
    @PutMapping("update")
    public Result update(@RequestBody @Validated CourseUpdateDTO dto) {
        return new Result(courseService.update(dto));
    }

    @Operation(summary = "删除 - 单个课程")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(courseService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids) {
        return new Result(courseService.deleteBatch(ids));
    }

    @Operation(summary = "查询 - 单个课程")
    @GetMapping("select/{id}")
    public Result select(@PathVariable("id") Long id) {
        return new Result(courseService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated CoursePageDTO dto) {
        return new Result(courseService.page(dto));
    }

    @Operation(summary = "查询 - 所有课程")
    @GetMapping("list")
    public Result list() {
        return new Result(courseService.listAll());
    }
}
