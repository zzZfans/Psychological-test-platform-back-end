package com.cqjtu.mindassess.pojo.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel("用户名密码+短信验证码登录对象")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSmLoginDto {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String phone;

    @ApiModelProperty("短信验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;
}
