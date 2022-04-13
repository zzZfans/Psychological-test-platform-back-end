package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author author
 * @since 2022-04-06
 */
@Data
public class AssessResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联userid")
    private Long userId;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("测试类型")
    private String assessType;

    @ApiModelProperty("程度，0，正常；1轻度；2，中度；3，重度")
    private Integer resultLevel;

    @ApiModelProperty("测试时间，年")
    private Integer year;

    @ApiModelProperty("月")
    private Integer month;

    @ApiModelProperty("日")
    private Integer day;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("逻辑删除（更新）（0：未删除 1：删除）")
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;


}
