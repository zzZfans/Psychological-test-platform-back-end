package com.cqjtu.mindassess.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.enums.ShortMessageScenes;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.pojo.req.user.UserSmLoginDto;
import com.cqjtu.mindassess.pojo.req.user.UserSmRegisterDto;
import com.cqjtu.mindassess.service.IShortMessageCodeService;
import com.cqjtu.mindassess.service.ISysUserService;
import com.cqjtu.mindassess.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@Api(tags = {"用户控制器"})
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    IShortMessageCodeService smService;
    @Resource
    ISysUserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register/up")
    public ApiResponse<?> register(@Validated @RequestBody UserSmRegisterDto dto) {
        /**
         * 业务逻辑：
         * 1.判断用户名是否重复，手机号是否重复
         * 2.判断手机验证码是否正确
         * 3.添加用户表信息(初始化 用户名，密码，盐，电话号码)
         * 4.初始化用户角色表
         */
        String registerUsername = dto.getUsername();
        if( userService.queryUserByUsername(registerUsername) != null ) {
            throw new BusinessException("用户名已经存在");
        }
        String phoneNumber = dto.getPhoneNumber();
        if( userService.queryUserByPhone(phoneNumber) != null ) {
            throw new BusinessException("电话号码已被绑定");
        }
        if( !smService.confirmSmCode(dto.getPhoneNumber(), dto.getCode(), ShortMessageScenes.SM_REGISTER.scenes) ) {
            throw new BusinessException("验证码错误");
        }
        User user = new User();
        user.setUsername(registerUsername);
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        String encryptionPassword = MD5Util.encryption(dto.getPassword() + salt);
        user.setPassword(encryptionPassword);
        user.setPhoneNumber(phoneNumber);
        if( userService.createDefaultUser(user) ) {
            return ApiResponse.success();
        }
        return ApiResponse.fail(400,"注册失败",null);
    }

    @ApiOperation("用户手机验证码登录")
    @PostMapping("/login")
    public ApiResponse<?> login(@Validated @RequestBody UserSmLoginDto dto ) {
        /**
         * 业务逻辑：
         *  1.用户名 与 密码加盐  验证
         *  2.satoken登录
         *  3.返回token
         */
        // TODO
        return null;

    }
}
