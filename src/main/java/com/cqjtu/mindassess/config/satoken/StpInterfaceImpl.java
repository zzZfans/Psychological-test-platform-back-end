package com.cqjtu.mindassess.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangning
 */
@Component
public class StpInterfaceImpl implements StpInterface {


    // 返回一个账号所拥有的权限码集合
    @Override
    public List<String> getPermissionList(Object o, String s) {
        return null;
    }

    // 返回一个账号所拥有的角色标识集合
    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
