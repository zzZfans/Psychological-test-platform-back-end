package com.cqjtu.mindassess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.entity.Role;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.entity.UserRole;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.exception.SystemErrorException;
import com.cqjtu.mindassess.mapper.UserMapper;
import com.cqjtu.mindassess.service.IRoleService;
import com.cqjtu.mindassess.service.ISysUserService;
import com.cqjtu.mindassess.service.IUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<UserMapper, User> implements ISysUserService {

    @Resource
    UserMapper userMapper;
    @Resource
    IRoleService roleService;
    @Resource
    IUserRoleService userRoleService;

    /**
     * 创建普通用户时，默认的角色
     */
    private static final String CREATE_USER_DEFAULT_ROLE = "general";

    @Override
    public List<User> queryUserByUsernameOrPhoneNumber(String username, String phoneNumber) {
        return list(new LambdaQueryWrapper<User>()
                .eq(User::getUsername,username)
                .or()
                .eq(User::getPhoneNumber,phoneNumber));
    }

    @Override
    public User queryUserByUsername(String username) {
        return getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername,username));
    }

    @Override
    public User queryUserByPhone(String phoneNumber) {
        return getOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhoneNumber,phoneNumber));
    }

    @Override
    public boolean createDefaultUser(User user) {
        if( !save(user) ){
            return false;
        }
        // init user_role Table
        Role role = roleService.queryRoleByName(CREATE_USER_DEFAULT_ROLE);
        if( role == null ){
            throw new SystemErrorException("系统创建用户时绑定的默认角色错误 不存在该角色:" + DEFAULT_BATCH_SIZE);
        }
        Long userId = user.getId();
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(role.getId());
        return userRoleService.createUserRole(userRole);
    }
}
