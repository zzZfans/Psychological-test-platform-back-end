package com.cqjtu.mindassess.pojo.resp.automessage;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


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

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private LocalDateTime createTime;
}
