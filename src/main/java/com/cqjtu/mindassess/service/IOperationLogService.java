package com.cqjtu.mindassess.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.entity.OperationLog;

import java.util.Map;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author auther
 * @since 2022-04-13
 */
public interface IOperationLogService extends IService<OperationLog> {
    IPage<OperationLog> selectPage(Map<String, Object> params, Map<String,String> condition);
}
