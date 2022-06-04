package com.cqjtu.mindassess.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.common.SaveGroup;
import com.cqjtu.mindassess.common.UpdateGroup;
import com.cqjtu.mindassess.entity.Notice;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.pojo.req.notice.NoticePageReq;
import com.cqjtu.mindassess.pojo.req.notice.NoticeReq;
import com.cqjtu.mindassess.pojo.resp.notice.NoticeResp;
import com.cqjtu.mindassess.service.INoticeService;
import com.cqjtu.mindassess.util.EmptyChecker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author auther
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService noticeService;

    @ApiOperation(value = "添加公告")
    @LogOperation(value = "添加公告")
    @PostMapping("/add")
    public ApiResponse<?> save(@RequestBody @Validated(SaveGroup.class) NoticeReq req) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        Notice notice = BeanUtil.copyProperties(req, Notice.class);
        notice.setCreateId(userId);
        notice.setUpdateId(userId);
        boolean re = noticeService.save(notice);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "修改公告")
    @LogOperation(value = "修改公告")
    @PostMapping("/update")
    public ApiResponse<?> update(@RequestBody @Validated(UpdateGroup.class) NoticeReq req) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        Notice notice = BeanUtil.copyProperties(req, Notice.class);
        notice.setUpdateId(userId);
        boolean re = noticeService.updateById(notice);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "删除公告")
    @LogOperation(value = "删除公告")
    @GetMapping("/delete/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {
        boolean re = noticeService.removeById(id);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "公告分页查询")
    @LogOperation(value = "公告分页查询")
    @PostMapping("/page")
    public ApiResponse<?> page(@RequestBody NoticePageReq pageReq) {
        Page<Notice> page = noticeService.page(new Page<>(pageReq.getPage(), pageReq.getPageSize()),
                new LambdaQueryWrapper<Notice>()
                        .like(EmptyChecker.notEmpty(pageReq.getTitle()), Notice::getNoticeTitle, pageReq.getTitle()));
        IPage<NoticeResp> convert = page.convert(notice -> BeanUtil.copyProperties(notice, NoticeResp.class));
        return ApiResponse.success(convert);
    }

}
