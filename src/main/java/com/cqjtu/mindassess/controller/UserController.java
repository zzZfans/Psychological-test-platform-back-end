package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.pojo.vo.UserReq;
import com.cqjtu.mindassess.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;


    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody UserReq user){
        userService.login(user);
        return ApiResponse.success(200,"登录成功",null);
    }
}
