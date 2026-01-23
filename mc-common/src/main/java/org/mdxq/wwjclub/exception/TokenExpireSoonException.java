package org.mdxq.wwjclub.exception;

/**
 * 类说明：登录令牌即将过期异常
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 11:58
 */
public class TokenExpireSoonException extends RuntimeException {
    public TokenExpireSoonException(String codeMessage){
        super(codeMessage);
    }
}
