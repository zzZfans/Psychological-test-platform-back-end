package com.cqjtu.mindassess.pojo.req.assess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author zhanzhencheng
 * 用户测试结果参数
 */
@Data
@ApiModel(value = "用户测试结果请求参数")
public class AssessResultReq {

    @ApiModelProperty(value = "userId唯一标识" ,required = true)
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty(value = "用户名称" ,required = true)
    @NotNull(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty(value = "测评类型" ,required = true)
    @NotBlank(message = "测评类型不能为空")
    private String assessType;

    @ApiModelProperty(value = "结果等级，0,正常；1，轻度；2，中度；3,重度" ,required = true)
    @NotNull(message = "结果等级不能为空")
    private Integer resultLevel;
}
