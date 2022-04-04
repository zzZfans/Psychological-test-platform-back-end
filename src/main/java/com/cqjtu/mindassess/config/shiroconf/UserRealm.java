package com.cqjtu.mindassess.config.shiroconf;

import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.service.IUserService;
import com.cqjtu.mindassess.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangning
 *
 *  Shiro 自定义Realm,通过连接数据库完成认证与授权
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("execute doGetAuthorizationInfo()");
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("execute doGetAuthenticationInfo()");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String crtUsername = token.getUsername();
        String crtPassword = String.valueOf(token.getPassword());
        log.info("当前未加密的密码: " + crtPassword);
        User user = userService.getUserByUsername(crtUsername);
        if( user !=null ){
            String encryptionPassword = MD5Util.encryption(crtPassword + user.getSalt());
            log.info("当前加密后的密码: " + encryptionPassword);
            log.info("数据库查询的加密后的密码: " + user.getPassword());
            return new SimpleAuthenticationInfo(user.getUsername(), encryptionPassword, "userRealm");

        }
        return null;
    }
}
