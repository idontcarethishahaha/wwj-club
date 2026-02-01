package org.mdxq.wwjclub.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;

/**
 * 类说明：菜单更新DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "菜单更新DTO")
public class MenuUpdateDTO {

    @Schema(description = "菜单ID")
    @NotNull(message = "菜单ID不能为空")
    private Long id;

    @Schema(description = "菜单名称")
    @NotEmpty(message = "菜单名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;

    @Schema(description = "菜单URL")
    @NotEmpty(message = "菜单URL不能为空")
    @Pattern(regexp = MC.Regex.MENU_URL_RE, message = MC.Regex.MENU_URL_RE_MSG)
    private String url;

    @Schema(description = "菜单图标")
    @NotEmpty(message = "菜单图标不能为空")
    @Pattern(regexp = MC.Regex.MENU_ICON_RE, message = MC.Regex.MENU_ICON_RE_MSG)
    private String icon;

    @Schema(description = "父菜单ID")
    @NotNull(message = "父菜单ID不能为空")
    @Min(value = 0,message = "菜单id不能小于0")
    private String pid;

    @Schema(description = "菜单介绍")
    @Pattern(regexp = MC.Regex.INFO_RE, message = MC.Regex.INFO_RE_MSG)
    private String info;
}
