package com.cqjtu.mindassess.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zhangning
 *
 *  用户注册请求对象d
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@ApiModel(value = "用户注册请求")
public class UserRegisterReq {

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;
    @ApiModelProperty(value = "邮箱")
    private String emailAddress;
    @ApiModelProperty(value = "验证码")
    private String code;
}
