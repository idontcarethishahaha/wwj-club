package org.mdxq.wwjclub.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.mdxq.wwjclub.dto.PageDTO;

/**
 * 类说明：分页查询部门DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 10:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询部门的DTO")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeptPageDTO extends PageDTO{
    @Schema(description = "部门名称")
    private String title;
}

