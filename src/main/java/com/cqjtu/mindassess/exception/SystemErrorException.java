package com.cqjtu.mindassess.exception;

/**
 * @author zhangning
 *
 * 系统错误异常
 */
public class SystemErrorException extends RuntimeException{

    public SystemErrorException() {
        super();
    }

    public SystemErrorException(String message) {
        super(message);
    }
}
