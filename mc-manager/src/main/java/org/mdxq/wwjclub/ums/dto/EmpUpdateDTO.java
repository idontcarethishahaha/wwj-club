package org.mdxq.wwjclub.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;

import java.time.LocalDateTime;

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

    // 登录账号
    @Schema(description = "登录账号")
    @NotEmpty(message = "登录账号不能为空")
    @Pattern(regexp = MC.Regex.USERNAME_RE, message = MC.Regex.USERNAME_RE_MSG)
    private String username;

//    // 登录密码
//    @Schema(description = "登录密码")
//    @NotEmpty(message = "登录密码不能为空")
//    @Pattern(regexp = MC.Regex.PASSWORD_RE, message = MC.Regex.PASSWORD_RE_MSG)
//    private String password;

    // 手机号码
    @Schema(description = "手机号码")
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = MC.Regex.PHONE_RE, message = MC.Regex.PHONE_RE_MSG)
    private String phone;

    // 微信信号码
    @Schema(description = "微信信号码")
    @NotEmpty(message = "微信信号码不能为空")
    private String wechat;

    // 邮箱地址
    @Schema(description = "邮箱地址")
    @NotEmpty(message = "邮箱地址不能为空")
    @Pattern(regexp = MC.Regex.EMAIL_RE, message = MC.Regex.EMAIL_RE_MSG)
    private String email;

//    // 员工性别：0女1男2保密
//    @Schema(description = "员工性别")
//    @NotNull(message = "员工性别不能为空")
//    private Integer gender;
//
//    // 员工年龄
//    @Schema(description = "员工年龄")
//    @NotNull(message = "员工年龄不能为空")
//    private Integer age;
//
//    // 籍贯省份
//    @Schema(description = "籍贯省份")
//    @NotEmpty(message = "籍贯省份不能为空")
//    private String province;

    // 现居住地
    @Schema(description = "现居住地")
    @NotEmpty(message = "现居住地不能为空")
    private String address;

    // 真实姓名
    @Schema(description = "真实姓名")
    @NotEmpty(message = "真实姓名不能为空")
    @Pattern(regexp = MC.Regex.REALNAME_RE, message = MC.Regex.REALNAME_RE_MSG)
    private String realname;

    // 身份证号
    @Schema(description = "身份证号")
    @NotEmpty(message = "身份证号不能为空")
    @Pattern(regexp = MC.Regex.ID_CARD_RE, message = MC.Regex.ID_CARD_RE_MSG)
    private String idcard;

    // 部门ID，部门表外键
    @Schema(description = "部门ID")
    @NotNull(message = "部门ID不能为空")
    private Long fkDeptId;

    // 员工描述
    @Schema(description = "员工描述")
    @Pattern(regexp = MC.Regex.INFO_RE, message = MC.Regex.INFO_RE_MSG)
//@NotEmpty(message = "不能为空")
    private String info;

    // 入职时间
    @Schema(description = "入职时间")
    @NotNull(message = "入职时间不能为空")
    private LocalDateTime hiredate;
}
