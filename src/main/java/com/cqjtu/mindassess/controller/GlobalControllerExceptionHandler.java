package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangning
 *
 *      全局异常处理器
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<?> businessExceptionHandler(BusinessException exception) {
        String message = exception.getMessage();
        return ApiResponse.fail(200,message,null);
    }

//    @ExceptionHandler(AuthorizationException.class)
//    public ApiResponse<?> authorizationExceptionHandler(AuthorizationException exception){
//        return ApiResponse.fail(403,"没有权限",null);
//    }
}
