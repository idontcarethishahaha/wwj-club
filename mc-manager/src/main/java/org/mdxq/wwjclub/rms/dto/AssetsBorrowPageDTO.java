package org.mdxq.wwjclub.rms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.mdxq.wwjclub.dto.PageDTO;

/**
 * 类说明：分页查询资产申请DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 11:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询资产申请的DTO")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetsBorrowPageDTO extends PageDTO {
    @Schema(description = "资产ID")
    private Long fkAssetsId;//根据资产ID查找

    @Schema(description = "员工ID")
    private Long fkEmpId;//根据员工ID查找
}
