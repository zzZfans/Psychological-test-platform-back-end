package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author auther
 * @since 2022-04-07
 */
@Getter
@Setter
@TableName("user_role")
@ApiModel(value = "UserRole对象", description = "用户角色关联表")
@NoArgsConstructor
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户角色关联主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @ApiModelProperty("用户主键")
    @TableField(value = "user_id")
    private Long userId;

    @ApiModelProperty("角色主键")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty("创建者 id")
    @TableField(value = "creator_id")
    private Long creatorId;

    @ApiModelProperty("更新者 id")
    @TableField(value = "updater_id")
    private Long updaterId;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
