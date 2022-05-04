package com.cqjtu.mindassess.pojo.req.captcha;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author: amosdzhn
 * @CreateTime: 2022/5/4 11:44
 */
@ApiModel("确认验证码请求")
@Data
public class ConfirmDto {

    @ApiModelProperty(value = "手机号码",example = "13240260311",required = true)
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    @ApiModelProperty(value = "验证码",example = "1111",required = true)
    @NotBlank(message = "验证码不能为空")
    private String code;
}
