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
 * 类说明：添加房间使用的DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 10:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "房间插入DTO")
public class RoomInsertDTO {
    /** 房间名称 */
    @Schema(description = "房间名称")
    @NotEmpty(message = "房间名称不能为空")
    // 正则校验规则
    //@Pattern(regexp = MC.Regex.TITLE_RE, message = MC.Regex.TITLE_RE_MSG)
    private String title;

    /** 房间地址 */
    @Schema(description = "所在位置")
    @NotEmpty(message = "房间位置不能为空")
    // 正则校验规则
    //@Pattern(regexp = MC.Regex.ADDRESS_RE, message = MC.Regex.ADDRESS_RE_MSG)
    private String address;

    /** 房间描述 */
    @Schema(description = "房间用途描述")
    private String info;

    /** 房间容量，单位人 */
    @Schema(description = "能够容纳多少人")
    @Min(value = 0, message = "房间容量要大于等于0")
    @NotNull(message = "房间容量不能为空")
    private Integer capacity;
}