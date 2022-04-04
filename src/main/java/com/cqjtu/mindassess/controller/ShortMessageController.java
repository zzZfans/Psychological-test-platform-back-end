package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
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
@Api(tags = {"短信控制器"})
@RestController
@RequestMapping("/sm")
public class ShortMessageController {

    @Autowired
    IShortMessageCodeService shortMessageCodeService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber",value = "电话号码"),
            @ApiImplicitParam(name = "scenes",value = "使用场景,例如注册(register)")
    })
    @ApiOperation(value = "短信验证码")
    @GetMapping("/code")
    public ApiResponse<?> requestSmCode(@RequestParam("phoneNumber") String phoneNumber,
                                        @RequestParam("scenes") String scenes){
        long expireTime = shortMessageCodeService.requestSmCode(phoneNumber, scenes);
        if( expireTime == -1){
            return ApiResponse.fail(400,"发送失败",null);
        }
        Map<String,Long> map = new HashMap<>();
        map.put("expireTime",expireTime);
        return ApiResponse.success(map);
    }
}
