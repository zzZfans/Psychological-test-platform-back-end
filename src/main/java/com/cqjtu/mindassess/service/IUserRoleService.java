package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author auther
 * @since 2022-04-07
 */
public interface IUserRoleService extends IService<UserRole> {


    /**
     * 给用户关联一个角色
     * @param userRole UserRole实例
     * @return 关联成功返回true
     */
    boolean createUserRole(UserRole userRole);


    /**
     * 根据用户id，查询该用户的 用户角色关系集合
     * @param userId
     * @return
     */
    List<UserRole> queryUserRoleByUserId(Long userId);
}
