package org.mdxq.wwjclub.component;

import lombok.extern.slf4j.Slf4j;
import org.mdxq.wwjclub.exception.*;
import org.mdxq.wwjclub.result.Result;
import org.mdxq.wwjclub.result.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 类说明：全局异常处理类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 14:17
 */
@RestControllerAdvice("org.mdxq.wwjclub")
@Slf4j
public class ExceptionAdvice {
    // AOP抛出异常通知
    @ExceptionHandler(ServerErrorException.class)
    public Object serverErrorException(ServerErrorException ex){
        String coderMessage = ex.getMessage();
        log.error("业务异常 - 服务器异常：{}",coderMessage);
        return new Result<>(ResultCode.SERVER_ERROR, coderMessage);
    }

    @ExceptionHandler(IllegalParamException.class)
    public Object illegalParamException(IllegalParamException ex){
        String coderMessage = ex.getMessage();
        log.error("业务异常 - 请求参数错误异常：{}",coderMessage);
        return new Result<>(ResultCode.ILLEGAL_PARAM, coderMessage);
    }

    @ExceptionHandler(RepeatRecordException.class)
    public Object repeatRecordException(RepeatRecordException ex){
        String coderMessage = ex.getMessage();
        log.error("业务异常 - 添加重复记录异常：{}",coderMessage);
        return new Result<>(ResultCode.REPEAT_RECORD, coderMessage);
    }

    @ExceptionHandler(TokenExpireException.class)
    public Object tokenExpireException(TokenExpireException ex){
        String coderMessage = ex.getMessage();
        log.error("业务异常 - Token过期：{}",coderMessage);
        return new Result<>(ResultCode.TOKEN_EXPIRED, coderMessage);
    }

    @ExceptionHandler(TokenExpireSoonException.class)
    public Object tokenExpireSoonException(TokenExpireSoonException ex){
        String coderMessage = ex.getMessage();
        log.error("业务异常 - Token即将过期：{}",coderMessage);
        return new Result<>(ResultCode.TOKEN_EXPIRING_SOON, coderMessage);
    }

    @ExceptionHandler(VersionException.class)
    public Object versionException(VersionException ex){
        String coderMessage = ex.getMessage();
        log.error("业务异常 - 记录版本号错误：{}",coderMessage);
        return new Result<>(ResultCode.SERVER_ERROR, coderMessage);
    }

    @ExceptionHandler(Exception.class)
    public Object Exception(Exception ex){
        String coderMessage = ex.getMessage();
        log.error("其他异常：{}",coderMessage);
        return new Result<>(ResultCode.SERVER_ERROR, coderMessage);
    }

    //参数校验错误异常
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Object hibernateValidatorException(BindException ex){
        BindingResult bindingResult = ex.getBindingResult();
        FieldError firstFieldError = bindingResult.getFieldErrors().get(0);
        //格式化错误消息
        String coderMessage = String.format("%s实例的%s属性校验失败：%s",
                firstFieldError.getObjectName(),
                firstFieldError.getField(),
                firstFieldError.getDefaultMessage());
        log.error("参数校验失败：{}",coderMessage);
        return new Result<>(ResultCode.ILLEGAL_PARAM, coderMessage);
    }
}
