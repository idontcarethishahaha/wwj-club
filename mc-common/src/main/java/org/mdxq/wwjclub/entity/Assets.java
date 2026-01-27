package org.mdxq.wwjclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类说明：资产实体类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 16:31
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assets implements Serializable {
    /** 主键 */
    private Long id;
    /** 资产名称 */
    private String title;
    /** 资产图片 */
    private String picture;
    /** 资产单价 */
    private Double price;
    /** 资产单价单位 */
    private String priceUnit;
    /** 剩余库存 */
    private Integer stock;
    /** 剩余库存单位 */
    private String stockUnit;
    /** 总计库存 */
    private Integer total;
    /** 资产描述 */
    private String info;
    /** 数据版本 */
    private Long version;
    /** 逻辑删除，0未删除，1已删除 */
    private Integer deleted;
    /** 创建时间 */
    private LocalDateTime created;
    /** 修改时间 */
    private LocalDateTime updated;
}

