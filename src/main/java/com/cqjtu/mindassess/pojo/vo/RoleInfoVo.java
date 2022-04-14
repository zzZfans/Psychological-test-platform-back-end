package com.cqjtu.mindassess.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleInfoVo {
    private Long id;
    private String roleName;
    private Integer status;
    private String description;
    private String creteBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
