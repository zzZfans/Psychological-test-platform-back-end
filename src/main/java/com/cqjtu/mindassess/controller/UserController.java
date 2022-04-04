package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.enums.ShortMessageScenes;
import com.cqjtu.mindassess.pojo.req.UserRegisterReq;
import com.cqjtu.mindassess.service.IUserService;
import com.cqjtu.mindassess.service.impl.ShortMessageCodeService;
import com.cqjtu.mindassess.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(tags = {"用户控制器"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    ShortMessageCodeService smService;

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody UserRegisterReq userRegisterReq){
        //用户名是否重复，手机号或邮箱是否重复
        String username = userRegisterReq.getUsername();
        String phoneNumber = userRegisterReq.getPhoneNumber();
        String emailAddress = userRegisterReq.getEmailAddress();
        if( userService.getUserByUsername(username) != null ){
            return ApiResponse.fail(400,"该用户名已存在",null);
        }
        if( phoneNumber != null && !phoneNumber.equals("") ){
            if( userService.getUserByPhoneNumber(phoneNumber) != null ){
                return ApiResponse.fail(400,"该手机号已被注册",null);
            }else {
                // 验证手机验证码正确性
                if ( !smService.confirmSmCode(phoneNumber,userRegisterReq.getCode(), ShortMessageScenes.SM_REGISTER.scenes)) {
                    return ApiResponse.fail(400,"验证码错误",null);
                }
            }
        }else if( emailAddress != null && !emailAddress.equals("")){
            // 邮箱注册
            if( userService.getUserByEmail(emailAddress) != null ){
                return ApiResponse.fail(400,"该邮箱已被绑定",null);
            }
            // TODO
            // 邮箱服务，发送验证码
        }
        // 持久化用户数据
        User user = new User();
        user.setUsername(userRegisterReq.getUsername());
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        String md5Password = MD5Util.encryption(userRegisterReq.getPassword() + salt);  // 加盐加密
        user.setPassword(md5Password);
        user.setPhoneNumber(phoneNumber);
        user.setEmailAddress(userRegisterReq.getEmailAddress());
        if( userService.addUser(user) != 1) {
            return ApiResponse.fail(400,"注册失败",null);
        }
        return ApiResponse.success(200,"注册成功",null);
    }
}
