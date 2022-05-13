package com.cqjtu.mindassess.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.entity.AutoMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.pojo.req.automessage.AutoMessagePageReq;
import com.cqjtu.mindassess.pojo.req.automessage.AutoMessageReq;
import com.cqjtu.mindassess.pojo.resp.automessage.AutoMessageResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auther
 * @since 2022-05-12
 */
public interface IAutoMessageService extends IService<AutoMessage> {

    /**
     * 添加消息
     * @param messageReq
     * @return
     */
    boolean saveAutoMessage(AutoMessageReq messageReq);

    /**
     * 分页查询自动消息
     * @param autoMessagePageReq
     * @return
     */
    Page<AutoMessageResp> pageList(AutoMessagePageReq autoMessagePageReq);
}
