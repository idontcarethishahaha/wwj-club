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
public class Room implements Serializable {
    private Long id;
    private String title;
    private Long version;
    private String address;
    private String info;
    private Integer deleted;
    private Integer capacity;//房间能容纳几人
    private LocalDateTime created;
    private LocalDateTime updated;
}
