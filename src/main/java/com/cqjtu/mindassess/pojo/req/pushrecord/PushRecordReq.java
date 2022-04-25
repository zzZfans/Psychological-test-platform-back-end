package com.cqjtu.mindassess.pojo.req.pushrecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhangzhencheng
 */
@Data
public class PushRecordReq {

    @ApiModelProperty(value = "userId唯一标识" ,required = true)
    @NotNull(message = "用户id不能为空")
    private Long receiverId;

    @ApiModelProperty(value = "推送消息" ,required = true)
    @NotBlank(message = "推送消息不能为空")
    private String message;

}
