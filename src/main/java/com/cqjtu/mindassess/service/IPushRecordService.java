package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.entity.PushRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.pojo.req.pushrecord.PushRecordReq;
import com.cqjtu.mindassess.pojo.resp.pushrecord.PushRecordResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auther
 * @since 2022-04-25
 */
public interface IPushRecordService extends IService<PushRecord> {

    /**
     * 添加消息
     * @param req
     * @return
     */
    Boolean saveRecord(PushRecordReq req);

    /**
     * 获取推送历史记录
     * @param receiverId
     * @return
     */
    List<PushRecordResp> getPushHistory(Long receiverId);

}
