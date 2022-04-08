package com.cqjtu.mindassess.controller.advice;

import cn.dev33.satoken.exception.SaTokenException;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.exception.SystemErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangning
 *
 *      全局异常处理器
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {


    /**
     * 系统错误异常处理
     * @param exception
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemErrorException.class)
    public ApiResponse<?> systemErrorHandler(SystemErrorException exception) {
        return ApiResponse.fail(500,exception.getMessage(),null);
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(BusinessException.class)
    public ApiResponse<?> businessExceptionHandler(BusinessException exception) {
        return ApiResponse.fail(200,exception.getMessage(),null);
    }

    /**
     * 参数校验全局异常处理器
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        Map<String,String> map = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ApiResponse.fail(400,"参数错误",map);
    }

    /**
     * 权限异常处理器
     * @param exception - SaToken顶级异常
     */
    @ExceptionHandler(SaTokenException.class)
    public ApiResponse<?> saTokenHandler(SaTokenException exception) {
        return ApiResponse.fail(200,"没有权限",exception.getMessage());
    }
}
