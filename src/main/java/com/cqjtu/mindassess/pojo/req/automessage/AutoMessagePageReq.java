package com.cqjtu.mindassess.pojo.req.automessage;

import com.cqjtu.mindassess.annotation.TypeCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhangzhencheng
 */
@Data
@ApiModel("自动消息分页查询参数")
public class AutoMessagePageReq {
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

    @ApiModelProperty("类型")
    @TypeCheck(value = {"all",""},message = "类型不正确")
    private String type;
}
