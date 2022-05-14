package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *  测试题目实体类
 * </p>
 *
 * @author zhangning
 * @since 2022-05-12
 */
@Getter
@Setter
@ApiModel(value = "Subject对象", description = "")
@TableName("subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("类型：1文本情绪识别题目")
    private Integer type;

    @ApiModelProperty("题目")
    private String subject;

    @ApiModelProperty("逻辑删除: 0 未删，1删")
    @TableLogic(value = "0",delval = "1")
    @TableField(value = "is_deleted")
    private Integer deleted;


}
