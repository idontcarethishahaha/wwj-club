package org.mdxq.wwjclub.ums.dto;

/**
 * 类说明：员工分页查询DTO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:12
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.mdxq.wwjclub.dto.PageDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "员工分页查询的DTO")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmpPageDTO extends PageDTO {
    private Long fkDeptId;//部门ID
}