package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author auther
 * @since 2022-05-12
 */
@Getter
@Setter
  @TableName("user_comment")
@ApiModel(value = "UserComment对象", description = "")
public class UserComment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("评论")
      private String content;

      @ApiModelProperty("用户id")
      private Long userId;

      @ApiModelProperty("用户名称")
      private String userName;

      @ApiModelProperty("父级评论用户名")
      private String parentUsername;

      @ApiModelProperty("用户头像")
      private String avatar;

      @ApiModelProperty("评论的根id")
      private Long topId;

      @ApiModelProperty("父级id")
      private Long parentId;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("逻辑删除（更新）（0：未删除 1：删除）")
      @TableLogic(value = "0",delval = "1")
      private Integer isDeleted;


}
