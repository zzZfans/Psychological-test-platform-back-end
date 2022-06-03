package com.cqjtu.mindassess.pojo.req.assess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 获取分析结果请求参数
 * @author zhangzhenchneg
 */
@Data
@ApiModel("分析结果请求参数")
public class AnalysisReq {
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;
}
