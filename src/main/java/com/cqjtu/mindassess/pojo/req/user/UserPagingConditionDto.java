package com.cqjtu.mindassess.pojo.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分页查询条件对象")
@Data
public class UserPagingConditionDto {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("状态")
    private Integer status;
}
