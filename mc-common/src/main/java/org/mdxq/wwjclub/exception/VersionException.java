package org.mdxq.wwjclub.exception;

/**
 * 类说明：乐观锁版本号错误异常
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 11:58
 */
public class VersionException extends RuntimeException {
    public VersionException(String codeMessage){
        super(codeMessage);
    }
}
