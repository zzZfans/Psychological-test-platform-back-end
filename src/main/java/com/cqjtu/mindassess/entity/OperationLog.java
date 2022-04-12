package com.cqjtu.mindassess.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author auther
 * @since 2022-04-13
 */
@Getter
@Setter
@ToString
  @TableName("operation_log")
@ApiModel(value = "OperationLog对象", description = "操作日志表")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("操作日志主键")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("用户主键")
      private Long userId;

      @ApiModelProperty("用户操作")
      private String operation;

      @ApiModelProperty("请求地址")
      private String link;

      @ApiModelProperty("执行时间（毫秒）")
      private Integer execTime;

      @ApiModelProperty("请求参数")
      private String params;

      @ApiModelProperty("请求 ip")
      private String ip;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;


}
