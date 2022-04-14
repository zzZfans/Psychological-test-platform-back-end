package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
@Getter
@Setter
  @ApiModel(value = "Role对象", description = "角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("角色主键")
      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("角色名")
      private String roleName;

      @ApiModelProperty("角色状态 0：启用 1：禁用")
      private Integer status;

      @ApiModelProperty("角色描述")
      private String description;

      @ApiModelProperty("创建者 id")
      @TableField(fill = FieldFill.INSERT)
      private Long creatorId;

      @ApiModelProperty("更新者 id")
      @TableField(fill = FieldFill.UPDATE)
      private Long updaterId;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除（更新）（0：未删除 1：删除）")
      private Integer isDeleted;


}
