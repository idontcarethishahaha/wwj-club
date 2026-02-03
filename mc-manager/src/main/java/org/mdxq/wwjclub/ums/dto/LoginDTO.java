package org.mdxq.wwjclub.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;

/**
 * 类说明：登录DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录DTO")
public class LoginDTO {

    @Schema(description = "用户名")
    @NotEmpty(message = "用户名不能为空")
    @Pattern(regexp = MC.Regex.USERNAME_RE, message = MC.Regex.USERNAME_RE_MSG)
    private String username;

    @Schema(description = "登录密码")
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = MC.Regex.PASSWORD_RE, message = MC.Regex.PASSWORD_RE_MSG)
    private String password;
}
