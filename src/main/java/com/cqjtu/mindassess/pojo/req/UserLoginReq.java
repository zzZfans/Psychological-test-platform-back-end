package com.cqjtu.mindassess.pojo.req;

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
    private Integer method;
    private String identity;
    private String credentials;
}
