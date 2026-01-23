package org.mdxq.wwjclub.exception;

/**
 * 类说明：登录令牌过期异常
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 11:58
 */
public class TokenExpireException extends RuntimeException {
    public TokenExpireException(String codeMessage){
        super(codeMessage);
    }
}
