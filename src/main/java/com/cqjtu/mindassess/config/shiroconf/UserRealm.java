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
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String crtUsername = token.getUsername();
        String crtPassword = String.valueOf(token.getPassword());
        User user = userService.getUserByUsername(crtUsername);
        if( user !=null ){
            String encryptionPassword = MD5Util.encryption(crtPassword + user.getSalt());
            token.setPassword(encryptionPassword.toCharArray());
            return new SimpleAuthenticationInfo(user, user.getPassword(), "userRealm");

        }
        return null;
    }
}
