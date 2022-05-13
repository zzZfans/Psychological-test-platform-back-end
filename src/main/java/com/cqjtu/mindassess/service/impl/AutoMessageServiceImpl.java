package com.cqjtu.mindassess.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.entity.AutoMessage;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.mapper.AutoMessageMapper;
import com.cqjtu.mindassess.pojo.req.automessage.AutoMessagePageReq;
import com.cqjtu.mindassess.pojo.req.automessage.AutoMessageReq;
import com.cqjtu.mindassess.pojo.resp.automessage.AutoMessageResp;
import com.cqjtu.mindassess.service.IAutoMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.util.EmptyChecker;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auther
 * @since 2022-05-12
 */
@Service
public class AutoMessageServiceImpl extends ServiceImpl<AutoMessageMapper, AutoMessage> implements IAutoMessageService {

    @Override
    public boolean saveAutoMessage(AutoMessageReq messageReq) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        AutoMessage autoMessage = new AutoMessage();
        autoMessage.setMessage(messageReq.getMessage());
        autoMessage.setCreateId(userId);
        autoMessage.setType(messageReq.getType());
        autoMessage.setUpdateId(userId);
        autoMessage.setTitle(messageReq.getTitle());

        return save(autoMessage);
    }

    @Override
    public Page<AutoMessageResp> pageList(AutoMessagePageReq pageReq) {
        Page<AutoMessage> page = new Page<>(pageReq.getPage(), pageReq.getPageSize());

        Page<AutoMessage> autoMessagePage = page(page, new LambdaQueryWrapper<AutoMessage>()
                .eq(EmptyChecker.notEmpty(pageReq.getType()), AutoMessage::getType, pageReq.getType()));

        IPage<AutoMessageResp> convert = autoMessagePage.convert(item -> BeanUtil.copyProperties(item, AutoMessageResp.class));
        return (Page<AutoMessageResp>) convert;
    }
}
