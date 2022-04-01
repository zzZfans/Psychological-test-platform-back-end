package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.pojo.req.UserLoginReq;
import com.cqjtu.mindassess.service.IAuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangning
 *
 *  用户登录登出
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    @PostMapping("/test")
    public String test(){
        Session session = SecurityUtils.getSubject().getSession();
        return ((String) session.getAttribute("userId"));
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody UserLoginReq userLoginReq){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if( subject.isAuthenticated() ){
            return ApiResponse.fail(200,"您已经登录",null);
        }
        authService.login(userLoginReq);
        session.setAttribute("userId","100");
        return ApiResponse.success("登录成功");
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout(){
        Subject subject = SecurityUtils.getSubject();
        if( !subject.isAuthenticated()){
            return ApiResponse.fail(200,"您尚未登录",null);
        }
        subject.logout();
        return ApiResponse.success("登录成功");
    }

    @PostMapping("/register")
    public ApiResponse<?> register(){
        //TODO
        return null;
    }
}
