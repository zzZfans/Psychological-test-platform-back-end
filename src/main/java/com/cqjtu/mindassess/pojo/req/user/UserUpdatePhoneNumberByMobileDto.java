package com.cqjtu.mindassess.pojo.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author: amosdzhn
 * @CreateTime: 2022/5/4 11:56
 */
@ApiModel("根据验证码修改用户手机号码请求对象")
@Data
public class UserUpdatePhoneNumberByMobileDto {

    @ApiModelProperty(value = "新手机号",example = "13240260311",required = true)
    @NotBlank(message = "手机号不能为空")
    private String phoneNumber;

    @ApiModelProperty(value = "验证码",example = "1111",required = true)
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
