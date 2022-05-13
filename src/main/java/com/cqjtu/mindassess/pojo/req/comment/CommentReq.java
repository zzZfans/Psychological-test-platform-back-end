package com.cqjtu.mindassess.pojo.req.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author author
 */
@Data
public class CommentReq {
    @ApiModelProperty("评论")
    @NotBlank(message = "评论不能为空")
    private String content;

    @ApiModelProperty("评论根id")
    @NotNull(message = "根id不能为空")
    private Long topId;

    @ApiModelProperty("父级评论id")
    @NotNull(message = "父级评论id不能为空")
    private Long parentId;
}
