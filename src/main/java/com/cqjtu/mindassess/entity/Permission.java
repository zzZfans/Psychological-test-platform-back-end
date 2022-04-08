package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "Permission对象", description = "权限表")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("权限主键")
      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("权限名")
      private String permissionName;

      @ApiModelProperty("VUE组件")
      private String component;

      @ApiModelProperty("VUE 组件名（会用作组成 VUE path，需符合 url 规则）")
      private String componentName;

      @ApiModelProperty("VUE 重定向路径")
      private String redirect;

      @ApiModelProperty("父级权限主键")
      private Long parentId;

      @ApiModelProperty("菜单图标")
      private String icon;

      @ApiModelProperty("权限")
      private String permission;

      @ApiModelProperty("权限类型 0：菜单 1：按钮")
      private Integer permissionType;

      @ApiModelProperty("状态 0：启用 1：禁用")
      private Integer status;

      @ApiModelProperty("权限描述")
      private String description;

      @ApiModelProperty("权限排序因子（升序）")
      private Integer sort;

      @ApiModelProperty("创建者 id")
      private Long creatorId;

      @ApiModelProperty("更新者 id")
      private Long updaterId;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("逻辑删除（更新）（0：未删除 1：删除）")
      private Integer isDeleted;
}
