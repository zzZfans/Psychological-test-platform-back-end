package com.cqjtu.mindassess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.entity.OperationLog;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.mapper.OperationLogMapper;
import com.cqjtu.mindassess.service.IOperationLogService;
import com.cqjtu.mindassess.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author auther
 * @since 2022-04-13
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

    @Resource
    ISysUserService userService;

    @Override
    public IPage<OperationLog> selectPage(Map<String, Object> params, Map<String,String> condition) {
        long current = Long.parseLong(params.get("current").toString());
        long pageSize = Long.parseLong(params.get("pageSize").toString());
        IPage<OperationLog> page = new Page<>(current, pageSize);
        LambdaQueryWrapper<OperationLog> operationLogLambdaQueryWrapper = new LambdaQueryWrapper<>();
        String username = condition.get("username");
        String IP = condition.get("ip");
        if (!ObjectUtils.isEmpty(username)) {
            User user = userService.queryUserByUsername(username);
            operationLogLambdaQueryWrapper.like(OperationLog::getUserId, user.getId());
        }
        if (!ObjectUtils.isEmpty(IP)) {
            operationLogLambdaQueryWrapper.like(OperationLog::getIp, IP);
        }
        return page(page, operationLogLambdaQueryWrapper.orderByDesc(true, OperationLog::getCreateTime));
    }

}
