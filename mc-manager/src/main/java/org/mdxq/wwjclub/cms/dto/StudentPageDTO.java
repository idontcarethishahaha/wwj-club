package org.mdxq.wwjclub.cms.dto;

/**
 * 类说明：学生分页查询DTO
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
@Schema(description = "学生分页查询DTO")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentPageDTO extends PageDTO {

    private String number;//学号
    private String realname;
    private Long fkSchoolId;//学校
    private Long fkClubId;//上课班级
}
