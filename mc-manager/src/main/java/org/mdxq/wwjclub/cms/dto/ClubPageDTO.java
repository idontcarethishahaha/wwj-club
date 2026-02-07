package org.mdxq.wwjclub.cms.dto;

/*
 * 类说明：分页查询班级DTO
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
@Schema(description = "分页查询班级的DTO")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClubPageDTO extends PageDTO {

    private String title;
    private Long fkDirectionId;
}