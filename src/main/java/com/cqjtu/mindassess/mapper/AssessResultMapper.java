package com.cqjtu.mindassess.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.entity.AssessResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqjtu.mindassess.pojo.req.assess.UserAssessRecordPageReq;
import com.cqjtu.mindassess.pojo.resp.assess.UserAssessBo;
import com.cqjtu.mindassess.pojo.resp.assess.UserAssessResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2022-04-06
 */
public interface AssessResultMapper extends BaseMapper<AssessResult> {

    /**
     * 获取已经测试了的用户列表
     * @param page
     * @param req
     * @return
     */
    Page<UserAssessResp> getUserAssessPage(Page<?> page, @Param("req") UserAssessRecordPageReq req);

    /**
     * 获取有心理问题记录的用户列表
     * @return
     */
    List<UserAssessBo> getProUserList();

}
