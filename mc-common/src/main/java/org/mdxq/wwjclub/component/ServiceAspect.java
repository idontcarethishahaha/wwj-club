package org.mdxq.wwjclub.component;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.mdxq.wwjclub.exception.IllegalParamException;
import org.springframework.stereotype.Component;

/**
 * 类说明：全局业务切面
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 14:51
 */
@Slf4j
@Aspect
@Component
public class ServiceAspect {
    @Around("execution(public * org.mdxq.wwjclub..service.impl.*.*(..))")
    @SneakyThrows
    public Object aroundAdvice(ProceedingJoinPoint jp) {
        Object[] args = jp.getArgs();//获取业务方法参数
        String className = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        //环绕通知的前置部分检查业务方法的参数是否有null值
        if(ObjectUtil.hasNull(args)){
            throw new IllegalArgumentException("业务方法"+methodName+"参数存在空值");
        }
        Object resultVal = jp.proceed(args);
        // 后置部分记录方法调用日志
        log.info(String.format(
                """
                        ========业务日志=======
                        业务方法：%s
                        参数：%s
                        返回结果：%s
                        """,methodName, JSONUtil.toJsonStr(resultVal),JSONUtil.toJsonStr(resultVal)
        ));
        return resultVal;
    }
}
