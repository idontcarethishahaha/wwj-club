package org.mdxq.wwjclub.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类说明：全局解决跨域访问限制
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 15:26
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 映射所有访问路径
                .allowedOriginPatterns("*") // 允许所有的域名对我发起跨域访问
                .allowCredentials(true) // 允许请求中携带cookie
                .allowedMethods("*") // 允许所有请求方式，包括：GET/POST/DELETE/PUT...
                .allowedHeaders("*") // 允许携带任意请求头
                .maxAge(1800); // 预检请求的超时时间，单位：毫秒
    }
}