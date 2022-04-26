package com.cqjtu.mindassess.pojo.resp.pushrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangzhencheng
 */
@Data
@ApiModel("推送记录列返回结果")
public class PushRecordResp {

    @ApiModelProperty("推送人名称")
    private String pusherName;

    @ApiModelProperty("推送消息")
    private String message;

    @ApiModelProperty("消息状态:0,未查看;1,已查看")
    private Integer status;

    @ApiModelProperty("创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
