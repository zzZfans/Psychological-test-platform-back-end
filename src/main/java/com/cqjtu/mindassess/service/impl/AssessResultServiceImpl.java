package com.cqjtu.mindassess.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqjtu.mindassess.entity.AssessResult;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.mapper.AssessResultMapper;
import com.cqjtu.mindassess.pojo.req.assess.AssessResultPageReq;
import com.cqjtu.mindassess.pojo.req.assess.AssessResultReq;
import com.cqjtu.mindassess.pojo.req.assess.RecordCountReq;
import com.cqjtu.mindassess.pojo.resp.assess.AssessResultResp;
import com.cqjtu.mindassess.service.IAssessResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.util.EmptyChecker;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
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
        LocalDateTime nowTime = LocalDateTime.now();
        assessResult.setCreateTime(nowTime);
        return baseMapper.insert(assessResult) > 0;
    }

    @Override
    public Page<AssessResultResp> pageList(AssessResultPageReq pageReq) {
        Page<AssessResult> page = new Page<>(pageReq.getPage(), pageReq.getPageSize());

        QueryWrapper<AssessResult> wrapper = new QueryWrapper<>();

        wrapper.lambda().eq(EmptyChecker.notEmpty(pageReq.getYear()), AssessResult::getYear, pageReq.getYear())
                .eq(EmptyChecker.notEmpty(pageReq.getMonth()), AssessResult::getMonth, pageReq.getMonth())
                .eq(EmptyChecker.notEmpty(pageReq.getDay()), AssessResult::getDay, pageReq.getDay())
                .eq(EmptyChecker.notEmpty(pageReq.getUserId()), AssessResult::getUserId, pageReq.getUserId())
                .eq(EmptyChecker.notEmpty(pageReq.getAssessType()), AssessResult::getAssessType, pageReq.getAssessType());

        Page<AssessResult> assessResultPage = baseMapper.selectPage(page, wrapper);
        IPage<AssessResultResp> infoIPage = assessResultPage
                .convert(item -> BeanUtil.copyProperties(item, AssessResultResp.class));
        return (Page<AssessResultResp>) infoIPage;
    }

    @Override
    public List<List<Integer>> recordCount(RecordCountReq req) {

        Long userId = ((User) StpUtil.getSession().get("user")).getId();

        Calendar now = Calendar.getInstance();
        Integer year = now.get(Calendar.YEAR);
        //默认年份为今年
        if (EmptyChecker.isEmpty(req.getYear())) {
            req.setYear(year);
        }
        //总量查询
        LambdaQueryWrapper<AssessResult> recordCountWrapper = new LambdaQueryWrapper<>();
        recordCountWrapper.eq(AssessResult::getYear, req.getYear())
                .eq(EmptyChecker.notEmpty(req.getType()), AssessResult::getAssessType, req.getType())
                .eq(AssessResult::getUserId, userId);
        List<AssessResult> list = this.list(recordCountWrapper);
        List<List<Integer>> result = new ArrayList<>();
        //测评总量
        result.add(getMonthCount(list));
        //测评中异常总量
        List<AssessResult> recordExcList = list.stream()
                .filter(item -> item.getResultLevel() > 0).collect(Collectors.toList());
        result.add(getMonthCount(recordExcList));
        return result;
    }

    private List <Integer> getMonthCount(List<AssessResult> list) {
        //将每月的总和相加
        Map<Integer, Integer> map = new HashMap<>();
        list.forEach(assessResult -> {
            if (map.containsKey(assessResult.getMonth())) {
                Integer count = map.get(assessResult.getMonth());
                map.put(assessResult.getMonth(), ++count);
            }else {
                map.put(assessResult.getMonth(), 1);
            }
        });
        //获取每月的测试总和
        List <Integer> everyMonthCount = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            if (map.containsKey(i)) {
                everyMonthCount.add(map.get(i));
            }else {
                everyMonthCount.add(0);
            }
        }
        return everyMonthCount;
    }
}
