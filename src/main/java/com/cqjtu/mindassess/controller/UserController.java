package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.pojo.vo.UserReq;
import com.cqjtu.mindassess.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "User控制器",description = "用户控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;


    @ApiOperation(value = "用户登录",notes = "用户登录")
    @PostMapping("/login")
    public ApiResponse<?> login(@ApiParam(name = "user",value = "用户信息") @RequestBody UserReq user){
        userService.login(user);
        return ApiResponse.success(200,"登录成功",null);
    }
}
