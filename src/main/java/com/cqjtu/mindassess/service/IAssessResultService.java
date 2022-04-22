package com.cqjtu.mindassess.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqjtu.mindassess.entity.AssessResult;
import com.cqjtu.mindassess.pojo.req.assess.AssessResultPageReq;
import com.cqjtu.mindassess.pojo.req.assess.AssessResultReq;
import com.cqjtu.mindassess.pojo.req.assess.RecordCountReq;
import com.cqjtu.mindassess.pojo.req.assess.UserAssessRecordPageReq;
import com.cqjtu.mindassess.pojo.resp.assess.AssessResultResp;
import com.cqjtu.mindassess.pojo.resp.assess.UserAnalysisResp;
import com.cqjtu.mindassess.pojo.resp.assess.UserAssessResp;

import java.util.List;
import java.util.Map;

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
    Page<AssessResultResp> pageList(AssessResultPageReq pageReq);

    /**
     * 测量总量以及异常统计查询
     * @param req
     * @return
     */
    List<List<Integer>> recordCount(RecordCountReq req);

    /**
     * 获取年份
     * @return
     */
    List<Integer> getYears();

    /**
     * 个人状态分析
     * @return
     */
    UserAnalysisResp getAnalysis();

    /**
     * 获取用户测试记录列表
     * @param req
     * @return
     */
    Page<UserAssessResp> getUserAssessRecord(UserAssessRecordPageReq req);

}
