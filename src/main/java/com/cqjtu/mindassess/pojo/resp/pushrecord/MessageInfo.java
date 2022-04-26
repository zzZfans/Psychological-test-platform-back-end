package com.cqjtu.mindassess.pojo.resp.pushrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangzhencheng
 */
@Data
@ApiModel("消息信息")
public class MessageInfo{
    @ApiModelProperty("消息id")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("消息")
    private String message;

    @ApiModelProperty("推送时间")
    private LocalDateTime createTime;

    @ApiModelProperty("消息状态")
    private Integer status;
}
