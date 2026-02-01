package org.mdxq.wwjclub.ums.dto;

/**
 * 类说明：分页查询菜单DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:12
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.mdxq.wwjclub.dto.PageDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询菜单的DTO")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuPageDTO extends PageDTO {
    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "父菜单ID")
    private Long pid;
}