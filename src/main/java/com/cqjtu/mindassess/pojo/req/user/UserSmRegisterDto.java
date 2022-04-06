package com.cqjtu.mindassess.pojo.req.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("用户名密码注册对象")
public class UserSmRegisterDto {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String phoneNumber;

    @ApiModelProperty("手机验证码")
    @NotBlank(message = "手机验证码不能为空")
    private String code;
}
