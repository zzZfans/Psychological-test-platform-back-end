package com.cqjtu.mindassess.pojo.vo.user;

import com.cqjtu.mindassess.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@ApiModel("")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoVo {

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
    @ApiModelProperty("")
    private String avatar;
    @ApiModelProperty("")
    private String faceRecognitionSource;
    @ApiModelProperty
    private String lastLoginIp;
    @ApiModelProperty
    private String lastLoginTime;
    private String stateChangerId;
    private LocalDateTime stateChangeTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    Set<Role> roles;
    Set<String> permissions;
}
