package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author auther
 * @since 2022-04-05
 */
@ApiModel(value = "Permission对象", description = "权限表")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("权限主键")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("权限名")
      private String permissionName;

      @ApiModelProperty("VUE 组件路径")
      private String componentPath;

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

    
    public Long getId() {
        return id;
    }

      public void setId(Long id) {
          this.id = id;
      }
    
    public String getPermissionName() {
        return permissionName;
    }

      public void setPermissionName(String permissionName) {
          this.permissionName = permissionName;
      }
    
    public String getComponentPath() {
        return componentPath;
    }

      public void setComponentPath(String componentPath) {
          this.componentPath = componentPath;
      }
    
    public String getComponentName() {
        return componentName;
    }

      public void setComponentName(String componentName) {
          this.componentName = componentName;
      }
    
    public String getRedirect() {
        return redirect;
    }

      public void setRedirect(String redirect) {
          this.redirect = redirect;
      }
    
    public Long getParentId() {
        return parentId;
    }

      public void setParentId(Long parentId) {
          this.parentId = parentId;
      }
    
    public String getIcon() {
        return icon;
    }

      public void setIcon(String icon) {
          this.icon = icon;
      }
    
    public String getPermission() {
        return permission;
    }

      public void setPermission(String permission) {
          this.permission = permission;
      }
    
    public Integer getPermissionType() {
        return permissionType;
    }

      public void setPermissionType(Integer permissionType) {
          this.permissionType = permissionType;
      }
    
    public Integer getStatus() {
        return status;
    }

      public void setStatus(Integer status) {
          this.status = status;
      }
    
    public String getDescription() {
        return description;
    }

      public void setDescription(String description) {
          this.description = description;
      }
    
    public Integer getSort() {
        return sort;
    }

      public void setSort(Integer sort) {
          this.sort = sort;
      }
    
    public Long getCreatorId() {
        return creatorId;
    }

      public void setCreatorId(Long creatorId) {
          this.creatorId = creatorId;
      }
    
    public Long getUpdaterId() {
        return updaterId;
    }

      public void setUpdaterId(Long updaterId) {
          this.updaterId = updaterId;
      }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }
    
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

      public void setUpdateTime(LocalDateTime updateTime) {
          this.updateTime = updateTime;
      }
    
    public Integer getIsDeleted() {
        return isDeleted;
    }

      public void setIsDeleted(Integer isDeleted) {
          this.isDeleted = isDeleted;
      }

    @Override
    public String toString() {
        return "Permission{" +
              "id=" + id +
                  ", permissionName=" + permissionName +
                  ", componentPath=" + componentPath +
                  ", componentName=" + componentName +
                  ", redirect=" + redirect +
                  ", parentId=" + parentId +
                  ", icon=" + icon +
                  ", permission=" + permission +
                  ", permissionType=" + permissionType +
                  ", status=" + status +
                  ", description=" + description +
                  ", sort=" + sort +
                  ", creatorId=" + creatorId +
                  ", updaterId=" + updaterId +
                  ", createTime=" + createTime +
                  ", updateTime=" + updateTime +
                  ", isDeleted=" + isDeleted +
              "}";
    }
}
