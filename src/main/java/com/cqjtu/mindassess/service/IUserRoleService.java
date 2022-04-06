package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
