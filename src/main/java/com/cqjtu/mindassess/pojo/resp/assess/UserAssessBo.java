package com.cqjtu.mindassess.pojo.resp.assess;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserAssessBo {
    @ApiModelProperty("用户id")
    private Long userId;

    private String assessType;
}
