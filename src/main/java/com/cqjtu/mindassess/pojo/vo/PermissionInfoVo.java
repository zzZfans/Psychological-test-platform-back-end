package com.cqjtu.mindassess.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PermissionInfoVo {
    private Long id;
    private String permissionName;
    private String component;
    private String routerName;
    private String redirect;
    private String icon;
    private String permission;
    private Integer permissionType;
    private Integer status;
    private String description;
    private Integer sort;
    private String creteBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
