package com.cqjtu.mindassess.pojo.vo.operation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("操作日志对象")
@Data
public class OperationVo {

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("执行时间")
    private Integer execTime;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("IP地址")
    private String ip;

    @ApiModelProperty("请求地址")
    private String link;

    @ApiModelProperty("操作")
    private String operation;

    @ApiModelProperty("请求参数")
    private String params;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;
}
