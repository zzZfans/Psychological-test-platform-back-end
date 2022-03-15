package com.cqjtu.mindassess.pojo.vo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhangning
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserReq implements Serializable {

    @ApiParam(name = "登录方式",value = "@ApiParam.value")
    private int loginMethod;  //登录方式
    @ApiParam(name = "用户名",value = "@ApiParam.username")
    private String username;
    private String password;
    private String phoneNumber;
    private String phoneCode;
    private String email;
    private String emailCode;

}
