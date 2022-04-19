package com.cqjtu.mindassess.pojo.vo.user;

import com.cqjtu.mindassess.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("用户分页查询响应对象")
@Data
public class UserPageVo {

    @ApiModelProperty("分页查询用户信息集合")
    private List<User> records;
    @ApiModelProperty("总数")
    private Long total;
}
