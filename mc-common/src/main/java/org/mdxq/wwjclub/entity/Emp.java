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
public class Emp implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String phone;
    private String wechat;
    private String email;
    private Integer gender;
    private Integer age;
    private String province;
    private String address;
    private String realname;
    private String idcard;
    private Long fkDeptId;
    private String info;
    private LocalDateTime hiredate;
    private Long version;
    private Integer deleted;
    private LocalDateTime created;
    private LocalDateTime updated;
}
