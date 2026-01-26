package org.mdxq.wwjclub.rms.dto;
/**
 * 类说明：分页查询学校DTO
 *
 * @author WuWenJin
 * @date 2026/1/26 9:30
 * @version 1.0
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.mdxq.wwjclub.dto.PageDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询学校DTO")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SchoolPageDTO extends PageDTO {

    @Schema(description = "学校名称")
    private String title;
}
