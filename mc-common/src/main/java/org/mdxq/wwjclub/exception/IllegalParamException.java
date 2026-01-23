package org.mdxq.wwjclub.exception;

/**
 * 类说明：请求参数非法异常
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 14:09
 */
public class IllegalParamException extends RuntimeException{
    public IllegalParamException(String codeMessage) {
        super(codeMessage);
    }
}
