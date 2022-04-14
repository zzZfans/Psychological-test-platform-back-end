package com.cqjtu.mindassess.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionInfoVo {
    private List<PermissionInfoVo> permissionList;
    private List<RoleInfoVo> roleList;
}
