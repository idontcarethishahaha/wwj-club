package org.mdxq.wwjclub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
public class AssetsBorrow implements Serializable {
    private Long id;
    private Long fkAssetsId;
    private Long fkEmpId;
    private Integer count;
    private LocalDateTime borrowTime;
    private LocalDateTime expectedReturnTime;
    private LocalDateTime returnTime;
    private String info;
    private Long version;
    private Integer deleted;
    private LocalDateTime created;
    private LocalDateTime updated;

    // 映射申请的资产
    private Assets assets;
    //映射申请人
    private Emp emp;
}
