package com.cqjtu.mindassess.pojo.req.captcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("请求验证码参数对象")
@Data
public class CaptchaDto {

    /**
     * 验证码接受者: recipient = 电话号码 | 邮箱地址
     */
    @ApiModelProperty(value = "接受验证码者,手机号，或邮箱",example = "13240260311 | 22850153@qq.com")
    @JsonProperty("value")
    @NotBlank(message = "请携带验证码接受者，手机号或邮箱")
    private String recipient;

    /**
     * 验证码使用场景: scene = login | register | update_mobile_phone_number
     *  login: 登录场景
     *  register: 注册场景
     *  update_mobile_phone_number: 修改移动手机号码场景
     */
    @ApiModelProperty(value = "验证码使用场景",example = "login | register | update_mobile_phone_number")
    @NotBlank(message = "请添加验证码使用场景")
    private String scene;

    /**
     * 验证码类型: type = mobile | email
     */
    @JsonProperty("captchaType")
    @ApiModelProperty(value = "验证码类型",example = "mobile | email")
    @NotBlank(message = "请添加验证码类型参数")
    private String type;
}
