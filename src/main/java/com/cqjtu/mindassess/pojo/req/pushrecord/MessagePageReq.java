package com.cqjtu.mindassess.pojo.req.pushrecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangzhencheng
 */
@Data
public class MessagePageReq {

    @ApiModelProperty(
            value = "当前页码，分页查询时传参数，列表查询不需要传",
            example = "1"
    )
    private int page = 1;
    @ApiModelProperty(
            value = "每页记录数，分页查询时传参数，列表查询不需要传",
            example = "5"
    )
    private int pageSize = 5;
}
