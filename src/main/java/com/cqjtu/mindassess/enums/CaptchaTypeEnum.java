package com.cqjtu.mindassess.enums;

/**
 * @author zhangning
 * <p>
 * 验证码类型
 */
public enum CaptchaTypeEnum {

    /**
     * 移动电话验证码类型
     */
    MOBILE("mobile"),
    /**
     * 电子邮箱验证码类型
     */
    EMAIL("email");


    public String captchaType;

    CaptchaTypeEnum(String captchaType) {
        this.captchaType = captchaType;
    }
}
