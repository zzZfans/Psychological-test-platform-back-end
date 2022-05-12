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
 * @since 2022-05-12
 */
@Getter
@Setter
@TableName("auto_message")
@ApiModel(value = "AutoMessage对象", description = "")
public class AutoMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("消息")
    private String message;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("类型,与测试结果类型对应,all: 通用类型")
    private String type;

    @ApiModelProperty("创建者id")
    private Long createId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新者id")
    private Long updateId;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除（更新）（0：未删除 1：删除）")
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;


}
