package com.cqjtu.mindassess.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.common.SaveGroup;
import com.cqjtu.mindassess.common.UpdateGroup;
import com.cqjtu.mindassess.entity.AutoMessage;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.pojo.req.automessage.AutoMessagePageReq;
import com.cqjtu.mindassess.pojo.req.automessage.AutoMessageReq;
import com.cqjtu.mindassess.pojo.resp.automessage.AutoMessageResp;
import com.cqjtu.mindassess.service.IAutoMessageService;
import com.cqjtu.mindassess.util.EmptyChecker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auther
 * @since 2022-05-12
 */
@RestController
@RequestMapping("/autoMessage")
public class AutoMessageController {

    @Autowired
    IAutoMessageService autoMessageService;

    @ApiOperation(value = "添加消息")
    @LogOperation(value = "添加消息")
    @PostMapping("/save")
    public ApiResponse<?> save(@RequestBody @Validated(SaveGroup.class) AutoMessageReq autoMessageReq) {
        boolean re = autoMessageService.saveAutoMessage(autoMessageReq);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "更新消息")
    @LogOperation(value = "更新消息")
    @PostMapping("/update")
    public ApiResponse<?> update(@RequestBody @Validated(UpdateGroup.class) AutoMessageReq autoMessageReq) {
        Long userId = ((User) StpUtil.getSession().get("user")).getId();
        AutoMessage autoMessage = BeanUtil.copyProperties(autoMessageReq, AutoMessage.class);
        autoMessage.setUpdateId(userId);
        boolean re = autoMessageService.updateById(autoMessage);
        return ApiResponse.success(re);
    }

    @ApiOperation(value = "分页查询消息")
    @LogOperation(value = "分页查询消息")
    @PostMapping("/page")
    public ApiResponse<?> page(@RequestBody @Validated AutoMessagePageReq pageReq) {
        Page<AutoMessageResp> autoMessageRespPage = autoMessageService.pageList(pageReq);
        return ApiResponse.success(autoMessageRespPage);
    }

    @ApiOperation(value = "删除消息")
    @LogOperation(value = "删除消息")
    @GetMapping("/delete/{id}")
    public ApiResponse<?> page(@PathVariable Long id) {
        if (EmptyChecker.isEmpty(autoMessageService.getById(id))) {
            throw new BusinessException("消息不存在");
        }
        boolean re = autoMessageService.removeById(id);
        return ApiResponse.success(re);
    }
}
