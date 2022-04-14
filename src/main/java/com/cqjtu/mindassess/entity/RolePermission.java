package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author auther
 * @since 2022-04-08
 */
@Getter
@Setter
  @TableName("role_permission")
@ApiModel(value = "RolePermission对象", description = "角色权限关联表")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("角色权限关联主键")
      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("角色主键")
      private Long roleId;

      @ApiModelProperty("权限主键")
      private Long permissionId;

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
