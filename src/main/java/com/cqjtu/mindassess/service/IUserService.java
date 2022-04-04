package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.pojo.req.UserRegisterReq;

/**
 * @author zhangning
 */
public interface IUserService {

    /**
     * 根据用户名，查询用户
     * @param username - 用户名
     * @return - 存在用户返回User,不存在返回null
     */
    User getUserByUsername(String username);

    /**
     * 根据用户移动电话号码查询用户
     * @param phone - 电话号码
     * @return - 存在用户返回User,不存在返回null
     */
    User getUserByPhoneNumber(String phone);

    /**
     * 根据邮箱查询用户
     * @param email - 邮箱
     * @return - 存在用户返回User,不存在返回null
     */
    User getUserByEmail(String email);


    /**
     * 添加用户
     * @param user -新增用户信息
     */
    int addUser(User user);
}
