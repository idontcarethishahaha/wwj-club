package org.mdxq.wwjclub.cms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * 类说明：班级进度DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/5 14:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "班级进度DTO")
public class ClubProgressInsertDTO {
    @Schema(description = "班级进度序号")
    @NotNull(message = "班级进度序号不能为空")
    private Integer idx;

    @Schema(description = "班级ID，班级表外键")
    @NotNull(message = "班级ID不能为空")
    private Long fkClubId;

    @Schema(description = "房间ID，房间表外键")
    @NotNull(message = "房间ID不能为空")
    private Long fkRoomId;

    @Schema(description = "班主任ID，员工表外键")
    @NotNull(message = "班主任ID不能为空")
    private Long fkManagerId;

    @Schema(description = "助理老师ID，员工表外键")
    @NotNull(message = "助理老师ID不能为空")
    private Long fkAssistantId;

    @Schema(description = "主讲老师ID，员工表外键")
    @NotNull(message = "主讲老师ID不能为空")
    private Long fkTeacherId;

    @Schema(description = "课程ID，课程表外键")
    @NotNull(message = "课程ID不能为空")
    private Long fkCourseId;

    @Schema(description = "进度开始时间")
    @NotNull(message = "进度开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "预计进度结束时间")
    @NotNull(message = "预计进度结束时间不能为空")
    private LocalDateTime expectedEndTime;

    @Schema(description = "实际进度结束时间")
    private LocalDateTime endTime;

    @Schema(description = "班级进度描述")
    private String info;
}
