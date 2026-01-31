package org.mdxq.wwjclub.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;
import java.util.Date;

/**
 * 类说明：员工信息更新DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "员工信息更新DTO")
public class EmpUpdateDTO {
    @Schema(description = "员工主键ID")
    @NotNull(message = "员工ID不能为空")
    private Long id;

    @Schema(description = "登录账号")
    @NotEmpty(message = "登录账号不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    @Size(max = 128, message = "登录账号长度不能超过128个字符")
    private String username;

    @Schema(description = "手机号码")
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "请输入正确格式的手机号码")
    private String phone;

    @Schema(description = "微信号码")
    @Size(max = 128, message = "微信号码长度不能超过128个字符")
    private String wechat;

    @Schema(description = "邮箱地址")
    @NotEmpty(message = "邮箱地址不能为空")
    @Email(message = "请输入正确格式的邮箱地址")
    @Size(max = 128, message = "邮箱地址长度不能超过128个字符")
    private String email;

    @Schema(description = "用户性别：0女1男2保密")
    @NotNull(message = "用户性别不能为空")
    @Min(value = 0, message = "性别值不能小于0")
    @Max(value = 2, message = "性别值不能大于2")
    private Integer gender;

    @Schema(description = "用户年龄")
    @NotNull(message = "用户年龄不能为空")
    @Min(value = 0, message = "年龄值不能小于0")
    @Max(value = 150, message = "年龄值不能大于150")
    private Integer age;

    @Schema(description = "籍贯省份")
    @Size(max = 128, message = "籍贯省份长度不能超过128个字符")
    private String province;

    @Schema(description = "现居住地")
    @Size(max = 256, message = "现居住地长度不能超过256个字符")
    private String address;

    @Schema(description = "真实姓名")
    @NotEmpty(message = "真实姓名不能为空")
    @Size(max = 128, message = "真实姓名长度不能超过128个字符")
    private String realname;

    @Schema(description = "身份证号")
    @NotEmpty(message = "身份证号不能为空")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "请输入正确格式的身份证号")
    private String idcard;

    @Schema(description = "部门表外键，所属部门")
    @NotNull(message = "所属部门不能为空")
    private Long fkDeptId;

    @Schema(description = "员工描述")
    @Size(max = 512, message = "员工描述长度不能超过512个字符")
    private String info;

    @Schema(description = "入职时间")
    @NotNull(message = "入职时间不能为空")
    private Date hiredate;
}
