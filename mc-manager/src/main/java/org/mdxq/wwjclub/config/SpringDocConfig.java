package org.mdxq.wwjclub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类说明：spring接口文档的配置类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 10:40
 */
@Configuration
public class SpringDocConfig {
    private static final String AUTHOR = "吴文瑾";
    private static final String URL = "https://localhost:23101/index.html";
    private static final String TITLE = "我的学习俱乐部";
    private static final String INFO = "wwj-club是基于SpringBoot开发的学生信息管理平台，可以管理和追踪学生的进度和状态";
    private static final String VERSION = "1.0.0";
    // http://localhost:23101/doc.html
    @Bean
    public OpenAPI commonInfo(){
        return new OpenAPI().info(
                new Info().title(TITLE)
                        .version(VERSION)
                        .description(INFO)
                        .contact(new Contact().name(AUTHOR).url(URL))
        );
    }
}
