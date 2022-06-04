package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.enums.ShortMessageScenes;
import com.cqjtu.mindassess.service.IShortMessageCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zhangning
 *
 */
@Deprecated
@Api(tags = {"短信控制器"})
@RestController
@RequestMapping("/sm")
public class ShortMessageController {

    @Autowired
    IShortMessageCodeService shortMessageCodeService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber",value = "电话号码")
    })
    @ApiOperation(value = "注册时短信验证码")
    @LogOperation(value = "注册时短信验证码")
    @GetMapping("/code/register")
    public ApiResponse<?> requestSmCodeForRegister(@RequestParam("phoneNumber") String phoneNumber){
        long expireTime = shortMessageCodeService.requestSmCode(phoneNumber, ShortMessageScenes.SM_REGISTER.scenes);
        Map<String,Long> map = new HashMap<>();
        map.put("expireTime",expireTime);
        return ApiResponse.success(map);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber",value = "电话号码")
    })
    @ApiOperation(value = "登录时短信验证码")
    @LogOperation(value = "登录时短信验证码")
    @GetMapping("/code/login")
    public ApiResponse<?> requestSmCodeForLogin(@RequestParam("phoneNumber") String phoneNumber){
        long expireTime = shortMessageCodeService.requestSmCode(phoneNumber, ShortMessageScenes.SM_LOGIN.scenes);
        Map<String,Long> map = new HashMap<>();
        map.put("expireTime",expireTime);
        return ApiResponse.success(map);
    }

}
