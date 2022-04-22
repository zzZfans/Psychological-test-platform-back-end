package com.cqjtu.mindassess.pojo.resp.assess;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangzhencheng
 */
@Data
public class UserAssessResp {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户电话")
    private String phoneNumber;

    @ApiModelProperty("用户性别")
    private Integer sex;

    @ApiModelProperty("用户最近测试时间")
    private LocalDateTime nearAssessTime;
}
