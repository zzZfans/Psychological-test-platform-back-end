package com.cqjtu.mindassess.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.entity.User;

import java.util.List;

/**
 * @author zhangning
 */
public interface ISysUserService extends IService<User> {

    /**
     * 根据用户名或者电话号码查询用户
     * @param username 用户名
     * @return 存在用户返回User集合，没有返回一个空集合
     */
    List<User> queryUserByUsernameOrPhoneNumber(String username, String phoneNumber);


    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 存在返回User实例，不存在返回null
     */
    User queryUserByUsername(String username);


    /**
     * 根据电话号码查询用户
     * @param phoneNumber 电话号码
     * @return 存在返回User实例，不存在返回null
     */
    User queryUserByPhone(String phoneNumber);

    /**
     * 创建一个默认角色的用户(默认角色为普通用户)
     * @param user 用户实体实例
     */
    boolean createDefaultUser(User user);
}
