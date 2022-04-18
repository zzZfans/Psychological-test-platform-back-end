package com.cqjtu.mindassess.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("查询权限结果对象")
@Data
public class PermissionVo {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("权限名")
    private String permissionName;

    @ApiModelProperty("Vue组件")
    private String component;

    @ApiModelProperty("路由名")
    private String routerName;

    @ApiModelProperty("重定向路径")
    private String redirect;

    @ApiModelProperty("父权限主键")
    private Long parentId;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("权限")
    private String permission;

    @ApiModelProperty("权限类型")
    private Integer permissionType;

    @ApiModelProperty("状态,0禁用，1启用")
    private Integer status;

    @ApiModelProperty("排序因子")
    private Integer sort;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("修改者")
    private String updateBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("子权限")
    private List<PermissionVo> children;
}
