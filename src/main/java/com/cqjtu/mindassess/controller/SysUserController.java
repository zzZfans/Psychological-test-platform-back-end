package com.cqjtu.mindassess.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.enums.AccessTokenName;
import com.cqjtu.mindassess.enums.ShortMessageScenes;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.pojo.req.user.UserSmLoginDto;
import com.cqjtu.mindassess.pojo.req.user.UserSmRegisterDto;
import com.cqjtu.mindassess.pojo.vo.user.LoginSuccessVo;
import com.cqjtu.mindassess.pojo.vo.user.UserInfoVo;
import com.cqjtu.mindassess.pojo.vo.user.UserNavVo;
import com.cqjtu.mindassess.service.IPermissionService;
import com.cqjtu.mindassess.service.IShortMessageCodeService;
import com.cqjtu.mindassess.service.ISysUserService;
import com.cqjtu.mindassess.util.JWTUtil;
import com.cqjtu.mindassess.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.math.Primes;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(tags = {"用户控制器"})
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    IShortMessageCodeService smService;
    @Resource
    ISysUserService userService;


    /**
     * 用于JWT签发的字符串中 claim中携带的 saTokenName
     */
    public static final String JWT_CLAIM_TOKEN_NAME = "sa-tokenName";

    /**
     * 用于JWT签发的字符串中 claim中携带的 saTokenValue
     */
    public static final String JWT_CLAIM_TOKEN_VALUE = "sa-tokenValue";

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
    @PostMapping("/login/phone")
    public ApiResponse<?> login(@Validated @RequestBody UserSmLoginDto dto ) {
        /**
         * 业务逻辑：
         *  1.用户名 与 密码加盐  验证
         *  2.手机验证码验证
         *  2.satoken登录
         *  3.返回token
         */
        User user = userService.checkEncryptionPassword(dto.getUsername(), dto.getPassword());
        boolean success = smService.confirmSmCode(dto.getPhone(), dto.getCode(), ShortMessageScenes.SM_LOGIN.scenes);
        if( !success ){
            throw new BusinessException("短信验证码错误");
        }
        if( StpUtil.isLogin() ){
            StpUtil.logout();
        }
        StpUtil.login(user.getUsername());
        StpUtil.getSession().set("user",user);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String tokenName = tokenInfo.getTokenName();
        String tokenValue = tokenInfo.getTokenValue();
        Map<String,Object> claims = new HashMap<>();
        claims.put(JWT_CLAIM_TOKEN_NAME,tokenName);
        claims.put(JWT_CLAIM_TOKEN_VALUE,tokenValue);
        String accessTokenStr = JWTUtil.createSimpleJwtString(claims, JWTUtil.DEFAULT_TIMEOUT, JWTUtil.DEFAULT_SALT);
        LoginSuccessVo loginSuccessVo = new LoginSuccessVo(AccessTokenName.ACCESS_TOKEN.tokenName, accessTokenStr);

        return ApiResponse.success(loginSuccessVo);

    }


    @ApiOperation("用户登出")
    @GetMapping("/logout")
    public ApiResponse<?> logout(){
        if( StpUtil.isLogin() ){
            StpUtil.logout();
            return ApiResponse.success(200,"登出成功",null);
        }
        return ApiResponse.fail(200,"尚未登录",null);
    }

    @ApiOperation("测试是否登录")
    @GetMapping("/test")
    public ApiResponse<?> test(){
        return ApiResponse.success(StpUtil.isLogin());
    }


    @ApiOperation("获取用户信息")
    @SaCheckRole(value = {"general","admin"},mode = SaMode.OR)
    @GetMapping("/info")
    public ApiResponse<?> userInfo(){
        String username = (String) StpUtil.getLoginId();
        UserInfoVo userInfoVo = userService.queryUserInfoByUsername(username);
        return ApiResponse.success(userInfoVo);
    }


    @ApiOperation("获取用户Nav")
    @GetMapping("/nav")
    public ApiResponse<?> userNav(){
        String username = (String) StpUtil.getLoginId();
        List<UserNavVo> userNavVos = userService.queryUserNavByUsername(username);
        return ApiResponse.success(userNavVos);
    }
}
