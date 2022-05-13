package com.cqjtu.mindassess.mapper;

import com.cqjtu.mindassess.entity.PushRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqjtu.mindassess.pojo.resp.pushrecord.PushRecordResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author auther
 * @since 2022-04-25
 */
public interface PushRecordMapper extends BaseMapper<PushRecord> {

    /**
     * 获取用户的推送历史记录
     * @param receiverId
     * @return
     */
    List<PushRecordResp> getPushHistory( @Param("id") Long receiverId);
}
