package com.cqjtu.mindassess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.entity.OperationLog;
import com.cqjtu.mindassess.mapper.OperationLogMapper;
import com.cqjtu.mindassess.service.IOperationLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    @Override
    public IPage<OperationLog> selectPage(Map<String, Object> params, OperationLog operationLog) {
        long current = Long.parseLong(params.get("current").toString());
        long pageSize = Long.parseLong(params.get("pageSize").toString());
        IPage<OperationLog> page = new Page<>(current, pageSize);
        LambdaQueryWrapper<OperationLog> operationLogLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Long userId = operationLog.getUserId();
        String ip = operationLog.getIp();
        if (!ObjectUtils.isEmpty(userId)) {
            operationLogLambdaQueryWrapper.like(OperationLog::getUserId, userId);
        }
        if (!ObjectUtils.isEmpty(ip)) {
            operationLogLambdaQueryWrapper.like(OperationLog::getIp, ip);
        }
        return page(page, operationLogLambdaQueryWrapper.orderByDesc(true, OperationLog::getCreateTime));
    }

}
