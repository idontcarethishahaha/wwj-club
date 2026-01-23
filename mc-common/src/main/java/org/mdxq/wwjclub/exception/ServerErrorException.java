package org.mdxq.wwjclub.exception;

/**
 * 类说明：服务器报错异常,代码 500
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 11:58
 */
public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String codeMessage){
        super(codeMessage);
    }
}
