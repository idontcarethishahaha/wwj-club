package org.mdxq.wwjclub.rms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.constant.MC;

/**
 * 类说明：修改资产的DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 10:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "资产更新DTO")
public class AssetsUpdateDTO {

    // 字符串用 NotEmpty
    // 数值类型用 NotNull

    @Schema(description = "资产ID")
    @NotNull(message = "资产ID不能为空")
    private Long id;
    /** 资产名称 */
    @Schema(description = "资产名称")
    @NotEmpty(message = "资产名称不能为空")
    @Pattern(regexp = MC.Regex.TITLE_RE,message = MC.Regex.TITLE_RE_MSG)
    private String title;
    /** 资产图片 */
    @Schema(description = "资产图片")
    private String picture;
    /** 资产单价 */
    @Schema(description = "资产单价")
    @NotNull(message = "资产单价不能为空")
    @DecimalMin(value = "0.01",message="单价不能少于0.01元")//单价最少一分钱
    private Double price;
    /** 资产单价单位 */
    @Schema(description = "资产单价单位")
    @NotEmpty(message = "资产单价单位不能为空")
    private String priceUnit;
    /** 剩余库存 */
    @Schema(description = "剩余库存")
    @NotNull(message = "剩余库存不能为空")
    @Min(value = 0,message="库存不能小于0")
    private Integer stock;
    /** 剩余库存单位 */
    @Schema(description = "剩余库存单位")
    @NotEmpty(message = "剩余库存单位不能为空")
    private String stockUnit;
    /** 总计库存 */
    @Schema(description = "总计库存")
    @NotNull(message = "总计库存不能为空")
    @Min(value = 0,message="总计库存不能小于0")
    private Integer total;
    /** 资产描述 */
    @Schema(description = "资产描述")
    @Pattern(regexp = MC.Regex. INFO_RE,message = MC.Regex.INFO_RE_MSG)
    private String info;
}