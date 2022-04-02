package com.cqjtu.mindassess.pojo.req;

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
public class UserRegisterReq {
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
}
