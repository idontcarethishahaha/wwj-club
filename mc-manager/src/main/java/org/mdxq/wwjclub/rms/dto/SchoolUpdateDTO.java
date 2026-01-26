package org.mdxq.wwjclub.rms.dto;
/**
 * 类说明：更新学校DTO
 *
 * @author WuWenJin
 * @date 2026/1/26 9:32
 * @version 1.0
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "学校添加DTO")
public class SchoolUpdateDTO {

    @Schema(description = "学校ID")
    @NotNull(message = "学校ID不能为空")
    private Long id;

    /** 学校名称 */
    @Schema(description = "学校名称")
    @NotEmpty(message = "学校名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;

    /** 学院名称 */
    @Schema(description = "学院名称")
    @NotEmpty(message = "学院名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String college;

    /** 专业名称 */
    @Schema(description = "专业名称")
    @NotEmpty(message = "专业名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String major;

    /** 班级名称 */
    @Schema(description = "班级名称")
    @NotEmpty(message = "班级名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String clazz;

    /** 学校地址 */
    @Schema(description = "地址")
    @NotEmpty(message = "地址不能为空")
    @Pattern(regexp = MC.Regex.ADDRESS_RE, message = MC.Regex.ADDRESS_RE_MSG)
    private String address;

    /** 学校描述 */
    @Schema(description = "学校简介")
    @Pattern(regexp = MC.Regex.INFO_RE, message = MC.Regex.INFO_RE_MSG)
    private String info;
}
