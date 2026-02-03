package org.mdxq.wwjclub.ums.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.entity.Emp;

/**
 * 类说明：登录VO
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    //保存登录令牌
    private String token;
    //保存员工信息
    private Emp emp;
}
