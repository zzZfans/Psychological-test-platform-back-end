package com.cqjtu.mindassess.pojo.req.permission;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "添加权限请求对象")
public class PermissionDto{


    @ApiModelProperty("权限名")
    @NotBlank(message = "权限名不能为空")
    private String permissionName;

    @ApiModelProperty("VUE组件")
    private String component;

    @ApiModelProperty("VUE 组件名（会用作组成 VUE path，需符合 url 规则）")
    private String routerName;

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

}
