package org.mdxq.wwjclub.rms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 类说明：资产申请插入DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/28 14:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "资产申请插入DTO")
public class AssetsBorrowInsertDTO {

    /** 资产ID，资产表外键 */
    @NotNull(message = "资产ID不能为空")
    @Schema(description = "资产ID")
    private Long fkAssetsId;

    /** 员工ID，员工表外键 */
    @NotNull(message = "员工ID不能为空")
    @Schema(description = "员工ID")
    private Long fkEmpId;

    /** 申请数量 */
    @NotNull(message = "申请数量不能为空")
    @Schema(description = "申请数量")
    @Min(value = 1, message = "申请数量要大于0")
    private Integer count;

    /** 申请时间 */
    @NotNull(message = "申请时间不能为空")
    @Schema(description = "申请时间")
    @Future(message = "申请时间不能早于当前时间")
    private LocalDateTime borrowTime;

    /** 预计归还时间 */
    @NotNull(message = "预计归还时间不能为空")
    @Schema(description = "预计归还时间")
    @Future(message = "预计归还时间不能早于当前时间")
    private LocalDateTime expectedReturnTime;

    /** 申请描述 */
    @Schema(description = "申请描述")
    private String info;
}