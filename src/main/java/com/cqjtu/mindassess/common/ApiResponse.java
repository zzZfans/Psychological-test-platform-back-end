package com.cqjtu.mindassess.common;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhangning
 * @param <T>
 *     通用响应
 */
@Data
public class ApiResponse <T> implements Serializable {

    // 业务成功标志
    private Boolean success;
    // 业务状态码
    private int code;
    // 业务信息
    private String message;
    // 业务数据
    private T result;
    // 响应时间
    private LocalDateTime time;


    public ApiResponse() {
    }

    private ApiResponse(Boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = data;
        this.time = LocalDateTime.now();
    }

    private ApiResponse(Boolean success,ResponseCode responseCode,T data){
        this(success,responseCode.code,responseCode.message,data);
    }

    public static <T> ApiResponse<?> success(){
        return ApiResponse.success(null);
    }

    public static <T> ApiResponse<?> success(T data){
        return new ApiResponse<>(true,ResponseCode.SUCCESS,data);
    }

    public static <T> ApiResponse<?> success(int code,String msg){
        return new ApiResponse<>(true,code,msg,null);
    }

    public static <T> ApiResponse<?> success(int code,String message,T data){
        return new ApiResponse<>(true,code,message,data);
    }

    public static <T> ApiResponse<?> success(ResponseCode rc,T data){
        return new ApiResponse<>(true,rc,data);
    }

    public static <T> ApiResponse<?> fail(int code,String message,T data) {
        return new ApiResponse<>(false,code,message,data);
    }

    public static <T> ApiResponse<?> fail(int code,String message) {
        return new ApiResponse<>(false,code,message,null);
    }
}
