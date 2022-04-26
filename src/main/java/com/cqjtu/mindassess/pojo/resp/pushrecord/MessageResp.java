package com.cqjtu.mindassess.pojo.resp.pushrecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangzhencheng
 */
@Data
@ApiModel("消息信息")
public class MessageResp {
    @ApiModelProperty("未读消息总量")
    private Long unreadCount;

    @ApiModelProperty("消息信息")
    private List<MessageInfo> messageInfos;
}

