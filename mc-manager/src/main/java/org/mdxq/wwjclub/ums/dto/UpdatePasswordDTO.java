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
 * 类说明：修改密码DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/31 14:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "修改密码DTO")
public class UpdatePasswordDTO {

    @Schema(description = "员工ID")
    @NotNull(message = "员工ID不能为空")
    private Long id;

    @Schema(description = "旧密码")
    @NotEmpty(message = "旧密码不能为空")
    @Pattern(regexp = MC.Regex.PASSWORD_RE, message = MC.Regex.PASSWORD_RE_MSG)
    private String oldPass;

    @Schema(description = "新密码")
    @NotEmpty(message = "新密码不能为空")
    @Pattern(regexp = MC.Regex.PASSWORD_RE, message = MC.Regex.PASSWORD_RE_MSG)
    private String newPass;
}
