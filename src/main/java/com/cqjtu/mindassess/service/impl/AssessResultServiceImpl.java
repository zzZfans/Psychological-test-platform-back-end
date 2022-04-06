package com.cqjtu.mindassess.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.entity.AssessResult;
import com.cqjtu.mindassess.mapper.AssessResultMapper;
import com.cqjtu.mindassess.pojo.req.AssessResultPageReq;
import com.cqjtu.mindassess.pojo.req.AssessResultReq;
import com.cqjtu.mindassess.pojo.vo.systeminfo.AssessResultInfo;
import com.cqjtu.mindassess.service.IAssessResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.util.EmptyChecker;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-04-06
 */
@Service
public class AssessResultServiceImpl extends ServiceImpl<AssessResultMapper, AssessResult> implements IAssessResultService {

    @Override
    public boolean saveAssessResult(AssessResultReq resultReq) {
        Calendar now = Calendar.getInstance();

        Integer year = now.get(Calendar.YEAR);
        Integer month = now.get(Calendar.MONTH) + 1;
        Integer day = now.get(Calendar.DAY_OF_MONTH);
        AssessResult assessResult = BeanUtil.copyProperties(resultReq, AssessResult.class);
        assessResult.setYear(year);
        assessResult.setMonth(month);
        assessResult.setDay(day);
        return baseMapper.insert(assessResult) > 0;
    }

    @Override
    public Page<AssessResultInfo> pageList(AssessResultPageReq pageReq) {
        Page<AssessResult> page = new Page<>(pageReq.getPage(), pageReq.getPageSize());

        QueryWrapper<AssessResult> wrapper = new QueryWrapper<>();

        wrapper.lambda().eq(EmptyChecker.notEmpty(pageReq.getYear()),AssessResult::getYear,pageReq.getYear())
                .eq(EmptyChecker.notEmpty(pageReq.getMonth()),AssessResult::getMonth,pageReq.getMonth())
                .eq(EmptyChecker.notEmpty(pageReq.getDay()),AssessResult::getDay,pageReq.getDay())
                .eq(EmptyChecker.notEmpty(pageReq.getUserId()),AssessResult::getUserId,pageReq.getUserId())
                .eq(EmptyChecker.notEmpty(pageReq.getAssessType()),AssessResult::getAssessType,pageReq.getAssessType());

        Page<AssessResult> assessResultPage = baseMapper.selectPage(page, wrapper);
        IPage<AssessResultInfo> infoIPage = assessResultPage
                .convert(item -> BeanUtil.copyProperties(item, AssessResultInfo.class));
        return (Page<AssessResultInfo>) infoIPage;
    }
}
