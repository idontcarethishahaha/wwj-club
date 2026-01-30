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
 * 类说明：部门插入DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 10:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "部门插入DTO")
public class DeptInsertDTO {
    @Schema(description = "部门名称")
    @NotEmpty(message = "部门名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;

    @Schema(description = "部门描述")
    @Pattern(regexp = MC.Regex.ADDRESS_RE, message = MC.Regex.ADDRESS_RE_MSG)
    private String info;

    /** 房间ID，房间表外键 */
    @NotNull(message = "房间ID不能为空")
    @Schema(description = "房间ID")
    private Long fkRoomId;

}
