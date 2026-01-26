package org.mdxq.wwjclub.rms.dto;

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
 * 类说明：添加学校DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 10:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学校添加DTO")
public class SchoolInsertDTO {
    //学校名称
    @Schema(description = "学校名称")
    @NotEmpty(message = "学校名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;
    //学院名称
    @Schema(description = "学院名称")
    @NotEmpty(message = "学院名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String college;
    //专业名称
    @Schema(description = "专业名称")
    @NotEmpty(message = "专业名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String major;
    //班级名称
    @Schema(description = "班级名称")
    @NotEmpty(message = "班级名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String clazz;
    //学校地址
    @Schema(description = "地址")
    @NotEmpty(message = "地址不能为空")
    @Pattern(regexp = MC.Regex.ADDRESS_RE, message = MC.Regex.ADDRESS_RE_MSG)
    private String address;
    // 学校描述
    @Schema(description = "学校简介")
    @Pattern(regexp = MC.Regex.ADDRESS_RE, message = MC.Regex.ADDRESS_RE_MSG)
    private String info;
}