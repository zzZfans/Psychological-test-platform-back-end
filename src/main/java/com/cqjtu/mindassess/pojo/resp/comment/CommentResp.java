package com.cqjtu.mindassess.pojo.resp.comment;

import com.cqjtu.mindassess.entity.UserComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lisong
 */
@Data
public class CommentResp extends UserComment {
    @ApiModelProperty("子评论数量")
    private Integer count;
}
