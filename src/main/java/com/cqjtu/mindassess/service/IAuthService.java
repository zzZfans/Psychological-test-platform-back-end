package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.pojo.req.UserLoginReq;

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

    void loginWithUsernamePassword(String username,String password);

    void loginWithShortMessageCode(String phoneNumber,String code);

    void loginWithEmailCode(String email,String code);
}
