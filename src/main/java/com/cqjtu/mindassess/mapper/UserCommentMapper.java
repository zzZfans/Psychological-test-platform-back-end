package com.cqjtu.mindassess.mapper;

import com.cqjtu.mindassess.entity.UserComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqjtu.mindassess.pojo.resp.comment.CommentResp;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author auther
 * @since 2022-05-12
 */
public interface UserCommentMapper extends BaseMapper<UserComment> {

    /**
     * 获取父评论列表
     * @return
     */
    List<CommentResp> getComments();

}
