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
 * 类说明：部门更新DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 10:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptUpdateDTO {

    @Schema(description = "部门ID")
    @NotNull(message = "部门ID不能为空")
    private Long id;

    @Schema(description = "部门名称")
    @NotEmpty(message = "部门名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;

    @Schema(description = "部门描述")
    @Pattern(regexp = MC.Regex.INFO_RE, message = MC.Regex.INFO_RE_MSG)
    private String info;

    /** 房间ID，房间表外键 */
    @NotNull(message = "房间ID不能为空")
    @Schema(description = "房间ID")
    private Long fkRoomId;
}
