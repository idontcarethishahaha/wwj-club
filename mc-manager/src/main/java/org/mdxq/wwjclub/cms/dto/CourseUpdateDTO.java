package org.mdxq.wwjclub.cms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;

/**
 * 类说明：课程更新DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "课程更新DTO")
public class CourseUpdateDTO {

    @Schema(description = "主键ID")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "课程名称")
    @NotEmpty(message = "课程名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;

    @Schema(description = "课程类别")
    @NotEmpty(message = "课程类别不能为空")
    private String category;

    @Schema(description = "课程顺序")
    @NotNull(message = "课程顺序不能为空")
    private Integer idx;

    @Schema(description = "课程学时")
    @NotNull(message = "课程学时不能为空")
    private Integer hours;

    @Schema(description = "课程描述")
    @Pattern(regexp = MC.Regex.INFO_RE, message = MC.Regex.INFO_RE_MSG)
    private String info;
}
