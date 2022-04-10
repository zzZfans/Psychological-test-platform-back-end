package com.cqjtu.mindassess.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.entity.Permission;
import com.cqjtu.mindassess.entity.Role;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.pojo.vo.user.UserInfoVo;
import com.cqjtu.mindassess.pojo.vo.user.UserNavVo;

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


    /**
     * 校验加密的密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return 校验成功返回User实例，用户名不存在或加密密码验证错误抛出业务异常
     */
    User checkEncryptionPassword(String username,String password);


    /**
     * 根据与用户名，查询用户信息
     * @param username - 用户名
     * @return 用户信息
     */
    UserInfoVo queryUserInfoByUsername(String username);


    /**
     * 根据用户名查询用户角色集合
     * @param username 用户名
     * @return 角色信息,若不存在返回null
     */
    List<Role> queryRolesByUsername(String username);


    /**
     * 根据用户名查询用户权限
     * @param username 用户名
     * @return
     */
    List<Permission> queryUserPermission(String username);


    /**
     * 根据用户名查询用户Nav信息
     * @param username 用户名
     * @return
     */
    List<UserNavVo> queryUserNavByUsername(String username);

}
