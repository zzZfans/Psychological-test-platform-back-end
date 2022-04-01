package com.cqjtu.mindassess.config.shiroconf;

import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangning
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
        User user = userService.getUserByUsername(crtUsername);
        if( user !=null ){
            SimpleAuthenticationInfo simpleAuthenticationInfo =
                    new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getSalt());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
