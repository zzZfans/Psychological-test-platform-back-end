package com.cqjtu.mindassess.pojo.req.assess;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangzhencheng
 * 测评结果分页查询请求参数
 */
@Data
@ApiModel("测试结果分页请求参数")
public class UserAssessRecordPageReq {

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

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "用户名")
    private String username;


}

