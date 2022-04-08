package com.cqjtu.mindassess.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.cqjtu.mindassess.entity.Role;
import com.cqjtu.mindassess.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangning
 */
@Component
public class StpInterfaceImpl implements StpInterface {


    @Autowired
    ISysUserService userService;


    // 返回一个账号所拥有的权限码集合
    @Override
    public List<String> getPermissionList(Object o, String s) {
        return null;
    }

    // 返回一个账号所拥有的角色标识集合
    // Object 是  Stp.login(Object o) 中的 o , 该系统o 为用户名
    @Override
    public List<String> getRoleList(Object o, String s) {
        String username = (String) o;
        List<Role> roles = userService.queryRolesByUsername(username);
        if( roles == null ){
            return null;
        }
        List<String> rolesStr = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
        return rolesStr;
    }
}
