package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.pojo.req.UserLoginReq;

/**
 * @author zhangning
 */
public interface IAuthService {

    /**
     * 用户登录(认证)
     * 默认实现三种方式
     *  1.账户密码
     *  2.短信验证码
     *  3.邮箱
     * @param userLoginReq 用户认证信息
     */
    void login(UserLoginReq userLoginReq);

    /**
     * 用户名，密码登录
     * @param username - 用户名
     * @param password - 密码
     */
    void loginWithUsernamePassword(String username,String password);

    /**
     * 短信验证码登录
     * @param phoneNumber - 电话号码
     * @param code - 验证码
     */
    void loginWithShortMessageCode(String phoneNumber,String code);

    /**
     * 邮箱登录
     * @param email - 电子邮箱
     * @param code  - 验证码
     */
    void loginWithEmailCode(String email,String code);
}
