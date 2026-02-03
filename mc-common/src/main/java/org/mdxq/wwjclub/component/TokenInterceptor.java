package org.mdxq.wwjclub.component;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mdxq.wwjclub.exception.TokenExpireException;
import org.mdxq.wwjclub.exception.TokenExpireSoonException;
import org.mdxq.wwjclub.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 类说明：登录令牌校验拦截器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/3 9:55
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 只拦截Controller的方法请求
        if(!(handler instanceof HandlerMethod)) return true;
        // 从请求头中获取token令牌
        String token  = request.getHeader("token");
        if(StrUtil.isEmpty(token)){
            throw new TokenExpireException("Token不存在");
        }
        Map<String,Object> verifyResult=JwtUtil.parse(token);
        // 如果令牌块过期了
        if((boolean) verifyResult.get("expiringSoon")){
            //签发新令牌
            throw new TokenExpireSoonException((String) verifyResult.get("newToken"));
        }
        return true;
    }
}
