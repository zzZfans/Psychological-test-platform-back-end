package com.cqjtu.mindassess.pojo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserLoginReq {
    private Integer method;
    private String identity;
    private String credentials;
}
