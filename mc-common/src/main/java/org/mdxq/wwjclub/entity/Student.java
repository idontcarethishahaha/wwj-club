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
public class Student implements Serializable {
    private Long id;
    private String number;
    private String realname;
    private String avatar;
    private String phone;
    private Integer gender;
    private String wechat;
    private Integer age;
    private String province;
    private String address;
    private Long fkSchoolId;
    private Long fkSalesmanId;
    private Long fkClubId;
    private String idcard;
    private LocalDateTime studyDate;
    private String emergencyName;
    private String emergencyPhone;
    private Integer status;
    private String info;
    private Long version;
    private Integer deleted;
    private LocalDateTime created;
    private LocalDateTime updated;

    // 额外添加三个成员，分别映射学生的学校、班级、负责老师（员工对象）
    private School school;
    private Club club;
    private Emp counselor;//咨询老师
}
