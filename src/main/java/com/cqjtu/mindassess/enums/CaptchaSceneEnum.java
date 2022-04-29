package com.cqjtu.mindassess.enums;

/**
 * @author zhangning
 * <p>
 * 验证码使用场景
 */
public enum CaptchaSceneEnum {

    /**
     * 验证码-登录场景
     */
    LOGIN("login"),
    /**
     * 验证码-注册场景
     */
    REGISTER("register"),
    /**
     * 验证码-修改用户电话场景
     */
    UPDATE_MOBILE_PHONE_NUMBER("update_mobile_phone_number");

    public String scene;

    CaptchaSceneEnum(String scene) {
        this.scene = scene;
    }
}
