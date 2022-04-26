package com.cqjtu.mindassess.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cqjtu.mindassess.entity.PushRecord;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.mapper.PushRecordMapper;
import com.cqjtu.mindassess.pojo.req.pushrecord.PushRecordReq;
import com.cqjtu.mindassess.pojo.resp.pushrecord.MessageInfo;
import com.cqjtu.mindassess.pojo.resp.pushrecord.MessageResp;
import com.cqjtu.mindassess.pojo.resp.pushrecord.PushRecordResp;
import com.cqjtu.mindassess.service.IPushRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auther
 * @since 2022-04-25
 */
@Service
public class PushRecordServiceImpl extends ServiceImpl<PushRecordMapper, PushRecord> implements IPushRecordService {

    @Override
    public Boolean saveRecord(PushRecordReq req) {
        Long pusherId = ((User) StpUtil.getSession().get("user")).getId();
        PushRecord pushRecord = new PushRecord();
        pushRecord.setTitle(req.getTitle());
        pushRecord.setPusherId(pusherId);
        pushRecord.setStatus(0);
        pushRecord.setMessage(req.getMessage());
        pushRecord.setReceiverId(req.getReceiverId());
        return save(pushRecord);
    }

    @Override
    public List<PushRecordResp> getPushHistory(Long receiverId) {
        return baseMapper.getPushHistory(receiverId);
    }

    @Override
    public MessageResp getPushMessage() {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        List<PushRecord> list = list(new LambdaQueryWrapper<PushRecord>().eq(PushRecord::getReceiverId, userId)
                .orderByAsc(PushRecord::getCreateTime));

        long count = list.stream().filter(item -> item.getStatus().equals(0)).count();

        List<MessageInfo> collect = list.stream().map(pushRecord -> {
            MessageInfo messageResp = BeanUtil.copyProperties(pushRecord, MessageInfo.class);
            return messageResp;
        }).collect(Collectors.toList());

        MessageResp messageResp = new MessageResp();
        messageResp.setMessageInfos(collect);
        messageResp.setUnreadCount(count);
        return messageResp;
    }
}
