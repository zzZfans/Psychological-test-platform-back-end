package com.cqjtu.mindassess.pojo.vo;

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

    private int loginMethod;  //登录方式
    private String username;
    private String password;
    private String phoneNumber;
    private String phoneCode;
    private String email;
    private String emailCode;

}
