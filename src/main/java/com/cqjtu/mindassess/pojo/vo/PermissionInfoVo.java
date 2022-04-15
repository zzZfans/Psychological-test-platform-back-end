package com.cqjtu.mindassess.pojo.vo;

import lombok.Data;


@Data
public class PermissionInfoVo {
    private Long id;
    private String icon;
    private String permission;
    private Long parentId;

}
