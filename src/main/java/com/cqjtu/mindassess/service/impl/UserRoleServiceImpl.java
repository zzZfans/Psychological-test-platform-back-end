package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.entity.UserRole;
import com.cqjtu.mindassess.mapper.UserRoleMapper;
import com.cqjtu.mindassess.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author auther
 * @since 2022-04-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Resource
    UserRoleMapper userRoleMapper;

    @Override
    public boolean createUserRole(UserRole userRole) {
        return save(userRole);
    }
}
