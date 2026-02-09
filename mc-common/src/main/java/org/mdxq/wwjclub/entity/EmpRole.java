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
public class EmpRole implements Serializable {
    private Long id;
    private Long fkEmpId;
    private Long fkRoleId;
    private Long version;
    private Integer deleted;
    private LocalDateTime created;
    private LocalDateTime updated;

    public EmpRole(Long fkEmpId, Long fkRoleId) {
        this.fkEmpId = fkEmpId;
        this.fkRoleId = fkRoleId;
    }
}
