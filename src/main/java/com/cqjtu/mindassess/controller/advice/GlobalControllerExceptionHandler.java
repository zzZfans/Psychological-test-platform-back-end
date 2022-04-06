package com.cqjtu.mindassess.controller.advice;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.exception.BusinessException;
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

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<?> businessExceptionHandler(BusinessException exception) {
        String message = exception.getMessage();
        return ApiResponse.fail(200,message,null);
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

}
