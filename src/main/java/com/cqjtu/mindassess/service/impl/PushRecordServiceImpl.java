package com.cqjtu.mindassess.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.cqjtu.mindassess.entity.PushRecord;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.mapper.PushRecordMapper;
import com.cqjtu.mindassess.pojo.req.pushrecord.PushRecordReq;
import com.cqjtu.mindassess.pojo.resp.pushrecord.PushRecordResp;
import com.cqjtu.mindassess.service.IPushRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
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
}
