package com.cqjtu.mindassess.pojo.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author: amosdzhn
 * @CreateTime: 2022/5/4 10:27
 */
@ApiModel("修改密码")
@Data
public class UserUpdatePasswordByMobileDto {

    @ApiModelProperty(value = "新密码",example = "newpwd...",required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty(value = "手机号码",example = "13240260311",required = true)
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    @ApiModelProperty(value = "短信验证码",example = "401325",required = true)
    @NotBlank(message = "短信验证码不能为空")
    private String mobileCaptcha;
}
