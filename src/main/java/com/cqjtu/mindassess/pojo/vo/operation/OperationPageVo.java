package com.cqjtu.mindassess.pojo.vo.operation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("分页操作日志Vo")
@Data
public class OperationPageVo {

    /**
     * 分页查询记录
     */
    @ApiModelProperty("分页查询记录集合")
    private List<OperationVo> records;

    /**
     * 总记录数
     */
    @ApiModelProperty("总记录数")
    private Long total;

}
