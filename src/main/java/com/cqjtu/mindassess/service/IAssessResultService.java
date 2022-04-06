package com.cqjtu.mindassess.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.entity.AssessResult;
import com.cqjtu.mindassess.pojo.req.AssessResultPageReq;
import com.cqjtu.mindassess.pojo.req.AssessResultReq;
import com.cqjtu.mindassess.pojo.vo.systeminfo.AssessResultInfo;

/**
 * <p>
 *  测试结果服务类
 * </p>
 *
 * @author author
 * @since 2022-04-06
 */
public interface IAssessResultService extends IService<AssessResult> {

    /**
     * 添加测试结果
     * @param resultReq
     * @return
     */
    boolean saveAssessResult(AssessResultReq resultReq);

    /**
     * 测试结果分页查询
     * @param pageReq
     * @return
     */
    Page<AssessResultInfo> pageList(AssessResultPageReq pageReq);

}
