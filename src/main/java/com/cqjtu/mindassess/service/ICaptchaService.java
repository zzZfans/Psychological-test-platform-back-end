package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.enums.CaptchaSceneEnum;
import com.cqjtu.mindassess.enums.CaptchaTypeEnum;

public interface ICaptchaService {


    /**
     * 请求验证码
     *
     * @param recipient    接受方法,通常是：移动手机号吗 或者是 电子邮箱
     * @param captchaType  - 验证码类型：通常是 电话类型 或 邮箱类型
     * @param captchaScene - 验证码使用场景: 通常是 注册，登录等
     */
    long requestCode(String recipient, CaptchaTypeEnum captchaType, CaptchaSceneEnum captchaScene);


    /**
     * 验证 code 是否正确
     *
     * @param recipient    接受方
     * @param code         - 接受的验证码
     * @param captchaType  - 验证码类型
     * @param captchaScene - 验证吗使用的场景
     * @return 验证正确返回true, 失败返回false
     */
    boolean confirmCode(String recipient, String code, CaptchaTypeEnum captchaType, CaptchaSceneEnum captchaScene);
}
