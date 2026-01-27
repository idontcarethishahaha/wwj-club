package org.mdxq.wwjclub.rms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.mdxq.wwjclub.dto.PageDTO;

/**
 * 类说明：分页查询资产DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 11:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询资产的DTO")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AssetsPageDTO extends PageDTO {
    @Schema(description = "资产名称")
    private String title;
}
