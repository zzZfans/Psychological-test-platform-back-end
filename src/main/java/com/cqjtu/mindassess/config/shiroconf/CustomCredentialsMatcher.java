package com.cqjtu.mindassess.config.shiroconf;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String originalPassword = (String) token.getCredentials();
        String sqlPassword = (String) info.getCredentials();
        System.out.println("originalPassword: " + originalPassword);
        System.out.println("sqlPassword: " + sqlPassword);
        return true;
    }
}
