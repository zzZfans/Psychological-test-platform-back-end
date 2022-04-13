package com.cqjtu.mindassess.pojo.req.captcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("请求验证码参数对象")
@Data
public class CaptchaDto {

    @ApiModelProperty("接受验证码者,手机号，或邮箱")
    @JsonProperty("value")
    @NotBlank(message = "请携带验证码接受者，手机号或邮箱")
    private String recipient;

    @ApiModelProperty("验证码使用场景")
    @NotBlank(message = "请添加验证码使用场景")
    private String scene;

    @JsonProperty("captchaType")
    @ApiModelProperty("验证码类型")
    @NotBlank(message = "请添加验证码类型参数")
    private String type;
}
