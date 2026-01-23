package org.mdxq.wwjclub.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 11:39
 */
@Data
//openapi生成接口文档时的注解
@Schema(description = "分页查询专用DTO")
public class PageDTO {
    @Schema(description = "当前是第几页")
    @Min(value = 0,message = "页号必须大于0")//参数校验注解，设置参数的最小值
    @NotNull(message = "页号不能为空")//参数校验注解，保证参数不为空
    private Integer pageNum;

    @Schema(description = "每页显示几条")
    @Min(value = 1,message = "每页至少显示1条")
    @NotNull(message = "每页显示条数不能为空")
    private Integer pageSize;

}
