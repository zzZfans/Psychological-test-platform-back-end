package com.cqjtu.mindassess.exception;

/**
 * @author zhangning
 *
 *      业务异常类，全部业务异常都通过该异常抛出，
 *      方便在GlobalControllerExceptionHandler中捕获集中处理
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
