package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.enums.ShortMessageScenes;
import com.cqjtu.mindassess.service.IShortMessageCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 凡森
 * @date 2022/4/11
 */
@CrossOrigin
@Api(tags = {"验证码控制器"})
@RestController
public class CaptchaController {

    @Autowired
    IShortMessageCodeService shortMessageCodeService;

    @ApiOperation(value = "发送验证码")
    @PostMapping("/captcha")
    public ApiResponse<?> sendCaptcha(@RequestBody Map<String, Object> data) {
        switch ((String) data.get("captchaType")) {
            case "mobile":
                long expireTime = shortMessageCodeService.requestSmCode((String) data.get("value"), ShortMessageScenes.SM_REGISTER.scenes);
                Map<String, Long> map = new HashMap<>();
                map.put("expireTime", expireTime);
                return ApiResponse.success(map);
            case "email":
                return ApiResponse.success();
            default:
                return ApiResponse.success();
        }
    }
}
