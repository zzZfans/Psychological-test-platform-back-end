package com.cqjtu.mindassess.pojo.req.assess;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangzhencheng
 */
@Data
public class RecordCountReq {

    @ApiModelProperty(value = "年")
    private Integer year;

    @ApiModelProperty(value = "月")
    private Integer month;

    @ApiModelProperty(value = "类型")
    private String type;
}
