package org.mdxq.wwjclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 类说明：实体类模板
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 16:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assets implements Serializable {
    private Long id;
    private String title;
    private String picture;
    private BigDecimal price;
    private String priceUnit;
    private Integer stock;
    private String stockUnit;
    private Integer total;
    private String info;
    private Long version;
    private Integer deleted;
    private LocalDateTime created;
    private LocalDateTime updated;
}
