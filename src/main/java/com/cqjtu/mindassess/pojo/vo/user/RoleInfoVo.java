package com.cqjtu.mindassess.pojo.vo.user;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleInfoVo {
    @ApiModelProperty("角色主键")
    private Long id;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色状态 0：启用 1：禁用")
    private Integer status;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("创建者 id")
    private Long creatorId;

    @ApiModelProperty("更新者 id")
    private Long updaterId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
