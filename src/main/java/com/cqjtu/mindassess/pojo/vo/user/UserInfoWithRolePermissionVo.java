package com.cqjtu.mindassess.pojo.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@ApiModel("用户信息")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoWithRolePermissionVo {

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户状态")
    private Integer status;
    @ApiModelProperty("移动电话号码")
    private String phoneNumber;
    @ApiModelProperty("电子邮件")
    private String emailAddress;
    @ApiModelProperty("性别")
    private Integer sex;
    @ApiModelProperty("出生日期")
    private LocalDate dateOfBirth;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("人脸识别源图 ")
    private String faceRecognitionSource;
    @ApiModelProperty("最近一次登录 ip")
    private String lastLoginIp;
    @ApiModelProperty("最后一次登录时间")
    private String lastLoginTime;
    @ApiModelProperty("状态变更者 id")
    private String stateChangerId;
    @ApiModelProperty("状态变更时间")
    private LocalDateTime stateChangeTime;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
    @ApiModelProperty("角色列表")
    Set<RoleInfo> roles;
    @ApiModelProperty("权限列表")
    Set<String> permissions;
}
