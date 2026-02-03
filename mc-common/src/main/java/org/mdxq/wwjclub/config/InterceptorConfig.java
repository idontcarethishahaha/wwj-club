package org.mdxq.wwjclub.config;

import jakarta.annotation.Resource;
import org.mdxq.wwjclub.component.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类说明：拦截器配置类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/3 10:08
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/api/v1/**")
                // 放行登录请求
                .excludePathPatterns("/api/v1/emp/loginByAccount");
    }
}
