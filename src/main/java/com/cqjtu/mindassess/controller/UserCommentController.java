package com.cqjtu.mindassess.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.entity.UserComment;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.pojo.req.comment.CommentReq;
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
    private IUserCommentService userCommentService;

    @ApiOperation(value = "添加评论")
    @PostMapping("/save")
    public ApiResponse<?> save(@RequestBody @Validated CommentReq commentReq) {
        if (commentReq.getTopId() != 0 && userCommentService.getById(commentReq.getTopId()) == null) {
            throw new BusinessException("评论不存在");
        }
        if (commentReq.getParentId() != 0 && userCommentService.getById(commentReq.getParentId()) == null) {
            throw new BusinessException("评论不存在");
        }
        User user = (User)StpUtil.getSession().get("user");
        UserComment comment = BeanUtil.copyProperties(commentReq, UserComment.class);
        comment.setUserId(user.getId());
        comment.setUserName(user.getUsername());
        comment.setAvatar(user.getAvatar());

        boolean re = userCommentService.save(comment);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "删除评论")
    @PostMapping("/remove/{id}")
    public ApiResponse<?> remove(@PathVariable Long id) {
        UserComment comment = userCommentService.getById(id);
        if (comment == null) {
            throw new BusinessException("评论不存在");
        }
        boolean re;
        // 删除顶层评论下所有评论
        if (comment.getParentId() == 0) {
            re = userCommentService.remove(new LambdaQueryWrapper<UserComment>()
                    .eq(UserComment::getTopId, id).eq(UserComment::getId, id));
        } else {
            re = userCommentService.removeById(id);
        }
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "获取评论列表")
    @PostMapping("/list")
    public ApiResponse<?> list() {
        List<UserComment> list = userCommentService.list(new LambdaQueryWrapper<UserComment>()
                .eq(UserComment::getParentId, 0).orderByDesc(UserComment::getCreateTime));
        return ApiResponse.success(list);
    }

    @ApiOperation(value = "获取某一评论下的子评论列表")
    @PostMapping("/childrenList/{id}")
    public ApiResponse<?> childrenList(@PathVariable Long id) {
        List<UserComment> list = userCommentService.list(new LambdaQueryWrapper<UserComment>()
                .eq(UserComment::getTopId, id).orderByDesc(UserComment::getCreateTime));
        return ApiResponse.success(list);
    }

}
