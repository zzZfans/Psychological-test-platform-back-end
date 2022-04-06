package com.cqjtu.mindassess.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangzhencheng
 * 测评结果分页查询请求参数
 */
@Data
@ApiModel("测试结果分页请求参数")
public class AssessResultPageReq {

    @ApiModelProperty(
            value = "当前页码，分页查询时传参数，列表查询不需要传",
            example = "1"
    )
    private int page = 1;
    @ApiModelProperty(
            value = "每页记录数，分页查询时传参数，列表查询不需要传",
            example = "10"
    )
    private int pageSize = 10;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "测试类型")
    private String assessType;

    @ApiModelProperty(value = "年")
    private Integer year;


    @ApiModelProperty(value = "月")
    private Integer month;

    @ApiModelProperty(value = "日")
    private Integer day;

}
