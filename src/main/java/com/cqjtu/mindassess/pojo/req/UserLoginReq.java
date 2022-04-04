package com.cqjtu.mindassess.pojo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhangning
 *
 *      用户登录(认证)接受参数对象
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserLoginReq {

    @ApiModelProperty(value = "登录方式" ,required = true)
    private Integer method;

    @ApiModelProperty(value = "用户名/电话号码/邮箱",required = true)
    private String identity;

    @ApiModelProperty(value = "密码/手机验证码/邮箱验证码",required = true)
    private String credentials;
}
