package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User implements Serializable {
    @TableId(type=IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String salt;
    private Integer status;
    @TableField("phone_number")
    private String phoneNumber;
    @TableField("email_address")
    private String emailAddress;
    private Integer sex;
    @TableField("date_of_birth")
    private LocalDateTime dateOfBirth;
    @TableField("face_recognition_source")
    private String faceRecognitionSource;
    @TableField("last_login_ip")
    private String lastLoginIp;
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
    @TableField("state_changer_id")
    private Long stateChangerId;
    @TableField("state_change_time")
    private LocalDateTime stateChangeTime;
    @TableField("create_time")
    private LocalDateTime createTime;
    @TableField("update_time")
    private LocalDateTime updateTime;
    @TableField("is_deleted")
    @TableLogic(value = "0",delval = "1")
    @JsonIgnore
    private Integer isDeleted;
}
