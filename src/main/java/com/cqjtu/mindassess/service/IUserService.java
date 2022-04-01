package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.User;

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
}
