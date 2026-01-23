package org.mdxq.wwjclub.exception;

/**
 * 类说明：添加记录重复异常
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 11:58
 */
public class RepeatRecordException extends RuntimeException {
    public RepeatRecordException(String codeMessage){
        super(codeMessage);
    }
}
