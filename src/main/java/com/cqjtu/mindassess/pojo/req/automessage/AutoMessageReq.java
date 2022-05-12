package com.cqjtu.mindassess.pojo.req.automessage;

import com.cqjtu.mindassess.annotation.TypeCheck;
import com.cqjtu.mindassess.common.SaveGroup;
import com.cqjtu.mindassess.common.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author zhangzhencheng
 */
@Data
@ApiModel("添加或修改自动消息对象参数")
public class AutoMessageReq {

    @ApiModelProperty("主键id")
    @NotNull(message = "修改id不能为空", groups = {UpdateGroup.class})
    @Null(message = "添加id必须为空", groups = {SaveGroup.class})
    private Long id;

    @ApiModelProperty("消息类型，对应测试结果类型")
    @TypeCheck(value = {"all"},message = "类型不正确", groups = {SaveGroup.class, UpdateGroup.class})
    @NotBlank(message = "类型不能为空", groups = {SaveGroup.class, UpdateGroup.class})
    private String type;

    @ApiModelProperty("消息")
    @NotBlank(message = "消息不能为空", groups = {SaveGroup.class, UpdateGroup.class})
    private String message;
}
