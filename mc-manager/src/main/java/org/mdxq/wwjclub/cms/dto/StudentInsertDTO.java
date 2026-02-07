package org.mdxq.wwjclub.cms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 类说明：班级插入DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "班级添加DTO")
public class StudentInsertDTO {

    @Schema(description = "班级名称")
    @NotEmpty(message = "班级名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;

    @Schema(description = "标签")
    @NotEmpty(message = "班级标签不能为空")
    //private String tag;
    private List<String> tag;

    @Schema(description = "方向ID外键")
    @NotNull(message = "方向ID外键不能为空")
    private Long fkDirectionId;

    @Schema(description = "开班时间")
    @NotNull(message = "开班时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "预计结课时间")
    @NotNull(message = "预计结课时间不能为空")
    @Future(message = "预计结课时间必须大于当前时间")
    private LocalDateTime expectedEndTime;

    @Schema(description = "实际结课时间")
    private LocalDateTime endTime;

    @Schema(description = "班级介绍")
    @Pattern(regexp = MC.Regex.INFO_RE, message = MC.Regex.INFO_RE_MSG)
    private String info;
}
