package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.enums.CaptchaSceneEnum;
import com.cqjtu.mindassess.enums.CaptchaTypeEnum;
import com.cqjtu.mindassess.enums.ShortMessageScenes;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.pojo.req.captcha.CaptchaDto;
import com.cqjtu.mindassess.service.ICaptchaService;
import com.cqjtu.mindassess.service.IShortMessageCodeService;
import com.cqjtu.mindassess.service.ISysUserService;
import com.cqjtu.mindassess.util.EmptyChecker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Autowired
    ICaptchaService captchaService;
    @Resource
    ISysUserService userService;

    @ApiOperation(value = "发送验证码")
    @PostMapping("/captcha")
    public ApiResponse<?> sendCaptcha(@RequestBody CaptchaDto dto) {
        String scene = dto.getScene();
        String captchaType = dto.getType();
        if (EmptyChecker.isEmpty(captchaType)) {
            throw new BusinessException("请携带验证码类型参数:captchaType");
        }if (EmptyChecker.isEmpty(scene)) {
            throw new BusinessException("请携带验证码使用场景参数:scene");
        }
        CaptchaTypeEnum typeEnum = null;
        if( CaptchaTypeEnum.MOBILE.captchaType.equals(captchaType)){
            typeEnum = CaptchaTypeEnum.MOBILE;
        }else if (CaptchaTypeEnum.EMAIL.captchaType.equals(captchaType)){
            typeEnum = CaptchaTypeEnum.EMAIL;
        }else {
            throw new BusinessException("不支持的验证码类型,请携带系统支持的captchaType参数(mobile,email)");
        }
        CaptchaSceneEnum sceneEnum = null;
        if( CaptchaSceneEnum.LOGIN.scene.equals(scene)){
            sceneEnum = CaptchaSceneEnum.LOGIN;
        }else if (CaptchaSceneEnum.REGISTER.scene.equals(scene)){
            sceneEnum = CaptchaSceneEnum.REGISTER;
        }else {
            throw new BusinessException("不支持的验证码场景,请携带系统支持的captchaScene参数");
        }
        String recipient = dto.getRecipient();
        long expireTime = captchaService.requestCode(recipient, typeEnum, sceneEnum);
        Map<String,Object> res = new HashMap<>();
        res.put("expireTime",expireTime);
        return ApiResponse.success(res);
    }
}
