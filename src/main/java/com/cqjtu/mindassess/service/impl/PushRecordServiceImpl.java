package com.cqjtu.mindassess.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.constans.MessageStatusCons;
import com.cqjtu.mindassess.entity.PushRecord;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.mapper.PushRecordMapper;
import com.cqjtu.mindassess.pojo.req.pushrecord.MessagePageReq;
import com.cqjtu.mindassess.pojo.req.pushrecord.PushRecordReq;
import com.cqjtu.mindassess.pojo.resp.pushrecord.MessageResp;
import com.cqjtu.mindassess.pojo.resp.pushrecord.PushRecordResp;
import com.cqjtu.mindassess.service.IAutoMessageService;
import com.cqjtu.mindassess.service.IPushRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.util.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    SensitiveWordService sensitiveWordService;

    @Override
    public Boolean saveRecord(PushRecordReq req) {
        Long pusherId = ((User) StpUtil.getSession().get("user")).getId();
        String content = HtmlUtil.getContent(req.getMessage());
        if (sensitiveWordService.judgeSensitivityWord(content)) {
            throw new BusinessException("存在敏感词");
        }
        PushRecord pushRecord = new PushRecord();
        pushRecord.setTitle(req.getTitle());
        pushRecord.setPusherId(pusherId);
        pushRecord.setStatus(MessageStatusCons.UNREAD);
        pushRecord.setMessage(req.getMessage());
        pushRecord.setReceiverId(req.getReceiverId());
        return save(pushRecord);
    }

    @Override
    public List<PushRecordResp> getPushHistory(Long receiverId) {
        return baseMapper.getPushHistory(receiverId);
    }

    @Override
    public Page<MessageResp> getPushMessage(MessagePageReq req) {
        Page<PushRecord> page = new Page<>(req.getPage(), req.getPageSize());
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        Page<PushRecord> messagePage = baseMapper.selectPage(page, (new LambdaQueryWrapper<PushRecord>().eq(PushRecord::getReceiverId, userId)
                .orderByDesc(PushRecord::getCreateTime)));

        IPage<MessageResp> respPage = messagePage
                .convert(item -> BeanUtil.copyProperties(item, MessageResp.class));

        return (Page<MessageResp>) respPage;
    }

    @Override
    public Integer getUnreadCount() {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        int count = count(new LambdaQueryWrapper<PushRecord>().eq(PushRecord::getReceiverId, userId)
                .eq(PushRecord::getStatus, MessageStatusCons.UNREAD));
        return count;
    }
}
