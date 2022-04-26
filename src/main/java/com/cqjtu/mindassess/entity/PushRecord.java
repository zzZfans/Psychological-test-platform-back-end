package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author auther
 * @since 2022-04-25
 */
@Getter
@Setter
@TableName("push_record")
@ApiModel(value = "PushRecord对象", description = "")
public class PushRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("推送人id")
    private Long pusherId;

    @ApiModelProperty("接收人id")
    private Long receiverId;

    @ApiModelProperty("消息标题")
    private String title;

    @ApiModelProperty("推送消息")
    private String message;

    @ApiModelProperty("消息状态:0,未查看;1,已查看")
    private Integer status;

    @ApiModelProperty("创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除（更新）（0：未删除 1：删除）")
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;


}
