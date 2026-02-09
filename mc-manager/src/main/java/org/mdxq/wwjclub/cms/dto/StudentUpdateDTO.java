package org.mdxq.wwjclub.cms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;

import java.time.LocalDateTime;

/**
 * 类说明：学生DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/7 9:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学生更新DTO")
public class StudentUpdateDTO {
    @Schema(description = "学号ID")
    @NotNull(message = "学号不能为空")
    private Long id;

    @Schema(description = "学生编号")
    @NotEmpty(message = "学生编号不能为空")
    private String number;

    @Schema(description = "学生姓名")
    @NotEmpty(message = "学生姓名不能为空")
    @Pattern(regexp = MC.Regex.REALNAME_RE, message = MC.Regex.REALNAME_RE_MSG)
    private String realname;

    @Schema(description = "手机号码")
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = MC.Regex.PHONE_RE, message = MC.Regex.PHONE_RE_MSG)
    private String phone;

    @Schema(description = "微信号")
    @NotEmpty(message = "微信号不能为空")
    private String wechat;

    @Schema(description = "现居住地")
    @NotEmpty(message = "现居住地不能为空")
    private String address;


    @Schema(description = "身份证号")
    @NotEmpty(message = "身份证号不能为空")
    @Pattern(regexp = MC.Regex.ID_CARD_RE, message = MC.Regex.ID_CARD_RE_MSG)
    private String idcard;

    @Schema(description = "紧急联系人姓名")
    @NotEmpty(message = "紧急联系人姓名不能为空")
    @Pattern(regexp = MC.Regex.REALNAME_RE, message = MC.Regex.REALNAME_RE_MSG)
    private String emergencyName;

    @Schema(description = "紧急联系人电话")
    @NotEmpty(message = "紧急联系人电话不能为空")
    @Pattern(regexp = MC.Regex.PHONE_RE, message = MC.Regex.PHONE_RE_MSG)
    private String emergencyPhone;

    /** 学校ID，学校表外键 */
    @Schema(description = "学校ID，学校表外键")
    @NotNull(message = "学校ID不能为空")
    private Long fkSchoolId;

    /** 咨询ID，员工表外键 */
    @Schema(description = "咨询ID，员工表外键")
    @NotNull(message = "咨询ID不能为空")
    private Long fkSalesmanId;

    /** 班级ID，班级表外键 */
    @Schema(description = "班级ID，班级表外键")
    @NotNull(message = "班级ID不能为空")
    private Long fkClubId;

    @Schema(description = "入学时间")
    @NotNull(message = "入学时间不能为空")
    private LocalDateTime studyDate;

    /** 学生状态：0-在培 1-结课 2-就业 3-休学 4-其它 */
    @Schema(description = "学生状态：0-在培 1-结课 2-就业 3-休学 4-其它")
    @NotNull(message = "学生状态不能为空")
    private Integer status;

    /** 学生描述 */
    @Schema(description = "学生描述")
    private String info;

}

