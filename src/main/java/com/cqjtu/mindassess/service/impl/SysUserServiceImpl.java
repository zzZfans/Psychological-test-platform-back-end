package com.cqjtu.mindassess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.entity.*;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.exception.SystemErrorException;
import com.cqjtu.mindassess.mapper.UserMapper;
import com.cqjtu.mindassess.pojo.vo.user.RoleInfo;
import com.cqjtu.mindassess.pojo.vo.user.UserInfoWithRolePermissionVo;
import com.cqjtu.mindassess.pojo.vo.user.UserNavVo;
import com.cqjtu.mindassess.service.*;
import com.cqjtu.mindassess.util.EmptyChecker;
import com.cqjtu.mindassess.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl extends ServiceImpl<UserMapper, User> implements ISysUserService {

    @Resource
    IRoleService roleService;
    @Resource
    IUserRoleService userRoleService;
    @Resource
    IRolePermissionService rolePermissionService;
    @Resource
    IPermissionService permissionService;

    /**
     * 创建普通用户时，默认的角色
     */
    private static final String CREATE_USER_DEFAULT_ROLE = "general";


    @Override
    public List<User> queryUserByUsernameOrPhoneNumber(String username, String phoneNumber) {
        return list(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .or()
                .eq(User::getPhoneNumber, phoneNumber));
    }

    @Override
    public User queryUserByUsername(String username) {
        return getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
    }

    @Override
    public User queryUserByPhone(String phoneNumber) {
        return getOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhoneNumber, phoneNumber));
    }

    @Override
    public boolean createDefaultUser(User user) {
        if (!save(user)) {
            return false;
        }
        // init user_role Table
        Role role = roleService.queryRoleByName(CREATE_USER_DEFAULT_ROLE);
        if (role == null) {
            throw new SystemErrorException("系统创建用户时绑定的默认角色错误 不存在该角色:" + CREATE_USER_DEFAULT_ROLE);
        }
        Long userId = user.getId();
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(role.getId());
        return userRoleService.createUserRole(userRole);
    }

    @Override
    public User checkEncryptionPassword(String username, String password) {
        User dbUser = queryUserByUsername(username);
        if (EmptyChecker.isEmpty(dbUser)) {
            throw new BusinessException("用户名不存在");
        }
        String loginPassword = MD5Util.encryption(password + dbUser.getSalt());
        if (!loginPassword.equals(dbUser.getPassword())) {
            throw new BusinessException("密码错误");
        }
        return dbUser;
    }

    @Override
    public UserInfoWithRolePermissionVo queryUserInfoByUsername(String username) {
        User user = queryUserByUsername(username);
        if (EmptyChecker.isEmpty(user)) {
            throw new BusinessException("用户不存在");
        }

        UserInfoWithRolePermissionVo userInfoVo = new UserInfoWithRolePermissionVo();
        BeanUtils.copyProperties(user, userInfoVo);

        Long userId = user.getId();
        // userId 查询 用户角色信息表
        List<UserRole> userRoles = userRoleService.queryUserRoleByUserId(userId);
        if (userRoles == null || userRoles.size() == 0) {
            userInfoVo.setRoles(new HashSet<>());
            userInfoVo.setPermissions(new HashSet<>());
        } else {
            Set<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
            List<Role> roles = roleService.queryRolesByIds(roleIds);
            Set<RoleInfo> roleSet = new HashSet<>();
            for (Role role : roles) {
                RoleInfo roleInfo = new RoleInfo();
                BeanUtils.copyProperties(role, roleInfo);
                roleSet.add(roleInfo);
            }
            userInfoVo.setRoles(roleSet);

            //根据roleId集合，查询所有权限id集合
            List<RolePermission> rolePermissions = rolePermissionService.list(
                    new LambdaQueryWrapper<RolePermission>()
                            .in(true, RolePermission::getRoleId,roleIds));

            if (rolePermissions == null || rolePermissions.size() == 0) {
                userInfoVo.setPermissions(new HashSet<>());
            } else {
                Set<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
                List<Permission> permissions = permissionService.listByIds(permissionIds);
                if (permissions == null || permissions.size() == 0) {
                    userInfoVo.setPermissions(new HashSet<>());
                } else {
                    Set<String> permissionSet = new HashSet<>();
                    for (Permission permission : permissions) {
                        //TODO
                        permissionSet.add(permission.getPermission());
                    }
                    userInfoVo.setPermissions(permissionSet);
                }
            }
        }
        return userInfoVo;
    }

    @Override
    public List<Role> queryRolesByUsername(String username) {
        User user = queryUserByUsername(username);
        if (EmptyChecker.isEmpty(user)) {
            throw new BusinessException("用户不存在");
        }

        Long userID = user.getId();
        List<UserRole> userRoles = userRoleService.queryUserRoleByUserId(userID);
        if (userRoles.size() == 0) {
            return null;
        }
        Set<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());

        List<Role> roles = roleService.queryRolesByIds(roleIds);

        return roles;
    }

    @Override
    public List<Permission> queryUserPermission(String username) {
        List<Role> roles = queryRolesByUsername(username);
        if (EmptyChecker.isEmpty(roles)) {
            return null;
        }
        Set<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
        List<RolePermission> rolePermissions = rolePermissionService.list(
                new LambdaQueryWrapper<RolePermission>()
                        .in(RolePermission::getRoleId, roleIds));
        if (EmptyChecker.isEmpty(rolePermissions)) {
            return null;
        }
        Set<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
        return permissionService.list(
                new LambdaQueryWrapper<Permission>()
                        .in(Permission::getId, permissionIds)
                        .orderByAsc(Permission::getSort));
    }

    @Override
    public List<UserNavVo> queryUserNavByUsername(String username) {
        List<Permission> permissions = queryUserPermission(username);
        if (EmptyChecker.isEmpty(permissions)) {
            return new ArrayList<>();
        }

        List<UserNavVo> collect = permissions.stream().map(permission -> {
            UserNavVo userNavVo = new UserNavVo();
            userNavVo.setName(permission.getRouterName());
            userNavVo.setParentId(permission.getParentId());
            userNavVo.setId(permission.getId());
            userNavVo.setComponent(permission.getComponent());
            userNavVo.setRedirect(permission.getRedirect());
            UserNavVo.Meta meta = new UserNavVo.Meta();
            meta.setIcon(permission.getIcon());
            meta.setTitle(permission.getPermissionName());
            userNavVo.setMeta(meta);
            return userNavVo;
        }).collect(Collectors.toList());

        return collect;
    }
}
