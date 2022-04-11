package com.cqjtu.mindassess.pojo.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("登录成功Vo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginSuccessVo {

//    @ApiModelProperty("token名,作为header或parameter的name")
//    private String tokenName;

    @ApiModelProperty("token值，作为header或parameter的value")
    private String token;
}
