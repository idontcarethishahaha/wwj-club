package org.mdxq.wwjclub.ums.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类说明：菜单VO类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/30 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {
    private Long id;
    private String title;
    private Long pid;//父菜单id
    private String parentTitle;//父菜单名称
}
