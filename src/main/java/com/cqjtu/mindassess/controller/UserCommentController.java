package com.cqjtu.mindassess.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.entity.UserComment;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.mapper.UserCommentMapper;
import com.cqjtu.mindassess.pojo.req.comment.CommentReq;
import com.cqjtu.mindassess.pojo.resp.comment.CommentResp;
import com.cqjtu.mindassess.service.IUserCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auther
 * @since 2022-05-12
 */
@RestController
@RequestMapping("/userComment")
public class UserCommentController {

    @Autowired
    private UserCommentMapper userCommentMapper;

    @ApiOperation(value = "添加评论")
    @PostMapping("/save")
    public ApiResponse<?> save(@RequestBody @Validated CommentReq commentReq) {
        if (commentReq.getTopId() != 0 && userCommentMapper.selectById(commentReq.getTopId()) == null) {
            throw new BusinessException("评论不存在");
        }
        if (commentReq.getParentId() != 0 && userCommentMapper.selectById(commentReq.getParentId()) == null) {
            throw new BusinessException("评论不存在");
        }
        User user = (User)StpUtil.getSession().get("user");
        UserComment comment = BeanUtil.copyProperties(commentReq, UserComment.class);
        comment.setUserId(user.getId());
        comment.setUserName(user.getUsername());
        comment.setAvatar(user.getAvatar());

        boolean re = userCommentMapper.insert(comment) > 0;
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "删除评论")
    @PostMapping("/remove/{id}")
    public ApiResponse<?> remove(@PathVariable Long id) {
        UserComment comment = userCommentMapper.selectById(id);
        if (comment == null) {
            throw new BusinessException("评论不存在");
        }
        boolean re;
        // 删除顶层评论下所有评论
        if (comment.getParentId() == 0) {
            re = userCommentMapper.delete(new LambdaQueryWrapper<UserComment>()
                    .eq(UserComment::getTopId, id).eq(UserComment::getId, id)) > 0;
        } else {
            re = userCommentMapper.deleteById(id) > 0;
        }
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "获取评论列表")
    @PostMapping("/list")
    public ApiResponse<?> list() {
        List<CommentResp> list = userCommentMapper.getComments();
        return ApiResponse.success(list);
    }

    @ApiOperation(value = "获取某一评论下的子评论列表")
    @PostMapping("/childrenList/{id}")
    public ApiResponse<?> childrenList(@PathVariable Long id) {
        List<UserComment> list = userCommentMapper.selectList(new LambdaQueryWrapper<UserComment>()
                .eq(UserComment::getTopId, id).orderByDesc(UserComment::getCreateTime));
        return ApiResponse.success(list);
    }

}
