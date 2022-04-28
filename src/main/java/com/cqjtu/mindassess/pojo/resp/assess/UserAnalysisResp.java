package com.cqjtu.mindassess.pojo.resp.assess;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author 光影
 */
@Data
public class UserAnalysisResp {

    @ApiModelProperty("近一个月各个类型测试分析后的情况")
    private Map<String, Integer> analysisDetails;

    @ApiModelProperty("近一个月各个类型测试总情况")
    private Map<String, List<Integer>> allTypeDetails;


    @ApiModelProperty("近一个月中最坏情况")
    private Map<String, Integer> nearTerror;

    @ApiModelProperty("近一个月中最严重的一次以后测试数量")
    private Map<String, Integer> nearTerrorBackCount;

    @ApiModelProperty("近一个月中最近的一次异常等级")
    private Map<String, Integer> nearExc;

    @ApiModelProperty("近一个月中最坏情况的时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Map<String, LocalDateTime> terrorTime;

    @ApiModelProperty("近一个月中最近的一次异常等级的时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Map<String, LocalDateTime> nearExcTIme;

    @ApiModelProperty("近一个月中最近的正常次数")
    private Map<String, Integer> nearNormalCount;


}
