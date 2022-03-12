package com.cqjtu.mindassess.common;

/**
 * @author zhangning
 *
 * 响应状态码枚举
 */
public enum ResponseCode {
    /**
     * 标准成功
     */
    SUCCESS(200,"OK"),
    /**
     * 标准失败
     */
    FAIL(600,"NO");


    public int code;
    public String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
