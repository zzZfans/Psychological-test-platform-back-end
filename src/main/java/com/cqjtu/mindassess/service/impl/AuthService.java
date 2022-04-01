package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.pojo.req.UserLoginReq;
import com.cqjtu.mindassess.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @author zhangning
 *
 *      登录服务具体实现
 */
@Service
@Slf4j
public class AuthService implements IAuthService {

    @Override
    public void login(UserLoginReq userLoginReq) {
        Integer method = userLoginReq.getMethod();
        if(method == 1 ){
            loginWithUsernamePassword(userLoginReq.getIdentity(), userLoginReq.getCredentials());
        }else if (method == 2){
            loginWithShortMessageCode(userLoginReq.getCredentials(), userLoginReq.getCredentials());
        }else if ( method == 3){
            loginWithEmailCode(userLoginReq.getIdentity(), userLoginReq.getCredentials());
        }else {
            throw new BusinessException("没有该登录方式");
        }
    }

    @Override
    public void loginWithUsernamePassword(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }catch (AuthenticationException ae){
            throw new BusinessException("用户名或密码错误");
        }
    }

    @Override
    public void loginWithShortMessageCode(String phoneNumber, String code) {

    }

    @Override
    public void loginWithEmailCode(String email, String code) {

    }
}
