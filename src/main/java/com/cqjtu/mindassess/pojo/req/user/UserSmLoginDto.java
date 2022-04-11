package com.cqjtu.mindassess.pojo.req.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel("用户登录对象")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSmLoginDto {

    @ApiModelProperty("登录类型")
    @JsonProperty
    public String loginType;

    @ApiModelProperty("身份,用户名|手机号码|邮箱")
    @NotBlank(message = "用户身份不能为空")
    public String identity;

    @ApiModelProperty("凭据,密码|手机验证码|短信验证码")
    @NotBlank(message = "用户凭据不能为空")
    public String credentials;
}
