package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.pojo.vo.UserReq;

/**
 * @author zhangning
 */
public interface IUserService {

    /**
     * 用户名密码登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回true
     */
    boolean usernamePasswordLogin(String username,String password);

    /**
     * 电话号码验证码登录
     * @param phoneNumber 电话号码
     * @param code 验证码
     * @return 登录成功返回true
     */
    boolean phoneNumberLogin(String phoneNumber,String code);

    /**
     * 邮箱验证码登录
     * @param email 电子邮件
     * @param code 验证码
     * @return 登录成功返回true
     */
    boolean emailLogin(String email,String code);

    /**
     * 统一登录
     * @param user 用户登录信息
     * @return 登录成功返回true
     */
    void login(UserReq user);
}
