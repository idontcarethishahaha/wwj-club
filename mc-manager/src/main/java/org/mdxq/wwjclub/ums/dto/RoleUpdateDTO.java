package org.mdxq.wwjclub.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;

/**
 * 类说明：更新角色DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色更新DTO")
public class RoleUpdateDTO {

    @Schema(description = "角色ID")
    @NotNull(message = "角色ID不能为空")
    private Long id;

    @Schema(description = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;

    @Schema(description = "角色描述")
    @Pattern(regexp = MC.Regex.ADDRESS_RE, message = MC.Regex.ADDRESS_RE_MSG)
    private String info;
}
