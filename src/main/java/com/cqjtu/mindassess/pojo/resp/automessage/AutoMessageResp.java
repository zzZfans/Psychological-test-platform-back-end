package com.cqjtu.mindassess.pojo.resp.automessage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author zhangzhencheng
 */
@Data
@ApiModel("自动消息返回信息")
public class AutoMessageResp {
    @ApiModelProperty("消息id")
    private Long id;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("消息")
    private String message;
}
