package org.mdxq.wwjclub.rms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.apache.ibatis.annotations.Delete;
import org.mdxq.wwjclub.dto.PageDTO;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.rms.dto.RoomInsertDTO;
import org.mdxq.wwjclub.rms.dto.RoomPageDTO;
import org.mdxq.wwjclub.rms.dto.RoomUpdateDTO;
import org.mdxq.wwjclub.rms.service.RoomService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类说明：房间管理控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 15:42
 *
 * http://localhost:23101/doc.html#/home
 */
@Tag(name = "房间管理")
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    @Operation(summary = "新增 - 单条记录")
    @PostMapping("save")
    public Result save(@RequestBody @Validated RoomInsertDTO dto){
        return new Result(roomService.save(dto));
    }

    @Operation(summary = "查询 - 单条记录")
    @GetMapping("selected/{id}")
    public Result select(@PathVariable("id") Long id){
        return new Result(roomService.getById(id));
    }

    @Operation(summary = "查询 - 分页查询")
    @GetMapping("page")
    public Result page(@Validated RoomPageDTO dto){
        return new Result(roomService.page(dto));
    }


    @Operation(summary = "更新 - 单条记录")
    @PostMapping("update")
    public Result update(@RequestBody @Validated RoomUpdateDTO dto){
        return new Result(roomService.update(dto));
    }

    @Operation(summary = "删除 - 单条记录")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return new Result(roomService.deleteById(id));
    }

    @Operation(summary = "删除 - 批量操作")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestParam("ids") List<Long> ids){
        return new Result(roomService.deleteBatch(ids));
    }
}
