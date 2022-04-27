package com.cqjtu.mindassess.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.constans.MessageStatusCons;
import com.cqjtu.mindassess.entity.PushRecord;
import com.cqjtu.mindassess.pojo.req.pushrecord.MessagePageReq;
import com.cqjtu.mindassess.pojo.req.pushrecord.PushRecordReq;
import com.cqjtu.mindassess.pojo.resp.pushrecord.MessageResp;
import com.cqjtu.mindassess.pojo.resp.pushrecord.PushRecordResp;
import com.cqjtu.mindassess.service.IPushRecordService;
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
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/pushRecord")
public class PushRecordController {

    @Autowired
    private IPushRecordService pushRecordService;

    @ApiOperation(value = "添加推送消息")
    @PostMapping("/save")
    public ApiResponse<?> save(@RequestBody @Validated PushRecordReq req) {
        boolean re = pushRecordService.saveRecord(req);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "获取推送记录")
    @GetMapping("/getPushHistory/{receiverId}")
    public ApiResponse<?> getPushHistory(@PathVariable Long receiverId) {
        List<PushRecordResp> re = pushRecordService.getPushHistory(receiverId);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "获取未读消息条数")
    @GetMapping("/getUnreadCount")
    public ApiResponse<?> getUnreadCount() {
        Integer re = pushRecordService.getUnreadCount();
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "用户获取推送信息")
    @PostMapping("/getPushMessage")
    public ApiResponse<?> getPushMessage(@RequestBody MessagePageReq req) {
        Page<MessageResp> re = pushRecordService.getPushMessage(req);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "更新消息状态")
    @GetMapping("/readMessage/{msgId}")
    public ApiResponse<?> readMessage(@PathVariable Long msgId) {
        boolean update = pushRecordService.update(new UpdateWrapper<PushRecord>().set("status", MessageStatusCons.ALREADY)
                .eq("id", msgId));
        return ApiResponse.success(update);
    }
}
