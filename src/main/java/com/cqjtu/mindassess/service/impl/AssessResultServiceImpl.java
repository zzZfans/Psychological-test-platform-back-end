package com.cqjtu.mindassess.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqjtu.mindassess.constans.AssessLevelCons;
import com.cqjtu.mindassess.constans.AssessTypeCons;
import com.cqjtu.mindassess.constans.LevelVoteThresholdCons;
import com.cqjtu.mindassess.entity.AssessResult;
import com.cqjtu.mindassess.entity.User;
import com.cqjtu.mindassess.mapper.AssessResultMapper;
import com.cqjtu.mindassess.pojo.req.assess.AssessResultPageReq;
import com.cqjtu.mindassess.pojo.req.assess.AssessResultReq;
import com.cqjtu.mindassess.pojo.req.assess.RecordCountReq;
import com.cqjtu.mindassess.pojo.req.assess.UserAssessRecordPageReq;
import com.cqjtu.mindassess.pojo.resp.assess.AssessResultResp;
import com.cqjtu.mindassess.pojo.resp.assess.UserAnalysisResp;
import com.cqjtu.mindassess.pojo.resp.assess.UserAssessResp;
import com.cqjtu.mindassess.service.IAssessResultService;
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

        Long userId = getUserId();

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
                .filter(item -> item.getResultLevel() > AssessLevelCons.NORMAL).collect(Collectors.toList());
        result.add(getMonthCount(recordExcList));
        return result;
    }

    @Override
    public List<Integer> getYears() {
        Long userId = getUserId();
        QueryWrapper<AssessResult> wrapper = new QueryWrapper<>();
        wrapper.lambda().select(AssessResult::getYear).eq(AssessResult::getUserId, userId)
                .groupBy(AssessResult::getYear);
        List<Object> objectList = baseMapper.selectObjs(wrapper);
        List<Integer> result = new ArrayList<>();
        objectList.forEach(o -> result.add(Integer.parseInt(o.toString())));

        return result;
    }

    @Override
    public UserAnalysisResp getAnalysis(Long userId) {
        //获取近期一个月的情况
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        // 设置为上一个月
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = calendar.getTime();
        //上一个月的所有记录,降序排序
        List<AssessResult> list = list(new LambdaQueryWrapper<AssessResult>().eq(AssessResult::getUserId, userId)
                .gt(AssessResult::getCreateTime, date).orderByDesc(AssessResult::getCreateTime));
        //最近没有记录的话直接返回
        if (list.size() == 0) {
            return new UserAnalysisResp();
        }
        UserAnalysisResp resp = new UserAnalysisResp();
        Map<String, List<Integer>> allTypeDetail = getAllTypeDetail(list);
        Map<String, List<AssessResult>> collect = list.stream().collect(Collectors.groupingBy(
                AssessResult::getAssessType
        ));
        resp = getNearNormalCount(collect, resp);
        resp = getTerror(collect, resp);
        resp.setAllTypeDetails(allTypeDetail);
        resp = getAnalysisDetail(resp, collect);

        resp.setAllTypeDetails(dealAllTypeDetail(resp));
        return resp;
    }

    @Override
    public Page<UserAssessResp> getUserAssessRecord(UserAssessRecordPageReq req) {
        Page<AssessResult> page = new Page<>(req.getPage(), req.getPageSize());
        Page<UserAssessResp> userAssessPage = baseMapper.getUserAssessPage(page, req);
        return userAssessPage;
    }

    @Override
    public Page<AssessResultResp> getUserHistory(AssessResultPageReq req) {
        Page<AssessResult> page = new Page<>(req.getPage(), req.getPageSize());

        Page<AssessResult> assessResultPage = page(page, new LambdaQueryWrapper<AssessResult>()
                .eq(EmptyChecker.notEmpty(req.getUserId()),AssessResult::getUserId, req.getUserId())
                .eq(EmptyChecker.notEmpty(req.getAssessType()), AssessResult::getAssessType, req.getAssessType())
                .orderByDesc(AssessResult::getCreateTime));

        IPage<AssessResultResp> convert = assessResultPage.convert(item -> BeanUtil.copyProperties(item, AssessResultResp.class));
        return (Page<AssessResultResp>) convert;
    }

    @Override
    public Map<String, Integer> getUserAnalysis(Long userId) {
        return getAnalysis(userId).getAnalysisDetails();
    }

    /**
     * 获取所有测评类型记录的详情结果
     *
     * @return
     */
    private Map<String, List<Integer>> getAllTypeDetail(List<AssessResult> list) {
        //用于保存用户的记录
        //key表示测试类型的记录
        //value表示等级出现的次数,数组的下标表示等级
        Map<String, List<Integer>> map = new HashMap<>();

        list.forEach(item -> {
            String key = item.getAssessType();
            if (map.containsKey(key)) {
                //测评结果记录的等级
                Integer level = item.getResultLevel();
                List<Integer> levelList = map.get(key);
                //该等级下出现的次数
                Integer num = levelList.get(level);
                levelList.set(level, ++num);
                map.put(item.getAssessType(), levelList);
            } else {
                List<Integer> list1 = Arrays.asList(0, 0, 0, 0);
                Integer level = item.getResultLevel();
                list1.set(level, 1);
                map.put(key, list1);
            }
        });
        return map;
    }
    private List<String> getTypeList() {
        List<String> types = new ArrayList<>();
        types.add(AssessTypeCons.BODY);
        types.add(AssessTypeCons.OPPOSE);
        types.add(AssessTypeCons.ANXIOUS);
        types.add(AssessTypeCons.DEPRESSION);
        types.add(AssessTypeCons.INTERPERSONAL);
        types.add(AssessTypeCons.OBSESSION);
        types.add(AssessTypeCons.PSYCHOSIS);
        types.add(AssessTypeCons.STUBBORN);
        types.add(AssessTypeCons.TERROR);

        return types;
    }


    private Map<String, List<Integer>> dealAllTypeDetail(UserAnalysisResp resp) {
        List<String> types = getTypeList();
        Map<String, List<Integer>> allTypeDetails = resp.getAllTypeDetails();

        for (String type : types) {
            if (!allTypeDetails.containsKey(type)) {
                List<Integer> list1 = Arrays.asList(0, 0, 0, 0);
                allTypeDetails.put(type, list1);
            }
        }

        return allTypeDetails;

    }

    /**
     * 获取最近一个月最坏情况的等级以及时间
     *
     * @return
     */
    private UserAnalysisResp getTerror(Map<String, List<AssessResult>> map, UserAnalysisResp resp) {
        Map<String, Integer> result = new HashMap<>();
        Map<String, LocalDateTime> resultTime = new HashMap<>();
        Map<String, Integer> terrorBackCountMap = new HashMap<>();
        Iterator<Map.Entry<String, List<AssessResult>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<AssessResult>> next = iterator.next();

            Integer terrorBackCount = 0;
            for (AssessResult temp : next.getValue()) {
                terrorBackCountMap.put(next.getKey(), terrorBackCount);
                terrorBackCount++;
                Integer level = temp.getResultLevel();
                if (!result.containsKey(next.getKey())) {
                    result.put(next.getKey(), level);
                    resultTime.put(next.getKey(), temp.getCreateTime());
                    continue;
                }
                Integer tempLevel = result.get(next.getKey());
                if (level > tempLevel) {
                    Integer tempBackCount = terrorBackCount - 1;
                    result.put(next.getKey(), level);
                    resultTime.put(next.getKey(), temp.getCreateTime());
                    terrorBackCountMap.put(next.getKey(), tempBackCount);
                }
            }
        }
        resp.setTerrorTime(resultTime);
        resp.setNearTerror(result);
        resp.setNearTerrorBackCount(terrorBackCountMap);
        return resp;
    }


    /**
     * 获取最近的正常次数,需要传入分组以后的map
     * 获取最近一次异常的等级以及时间
     *
     * @return
     */
    private UserAnalysisResp getNearNormalCount(Map<String, List<AssessResult>> map, UserAnalysisResp resp) {
        Map<String, Integer> result = new HashMap<>(16);
        Map<String, LocalDateTime> nearExcTime = new HashMap<>(16);
        Map<String, Integer> nearExc = new HashMap<>(16);
        Iterator<Map.Entry<String, List<AssessResult>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<AssessResult>> next = iterator.next();
            for (AssessResult temp : next.getValue()) {
                if (temp.getResultLevel() > AssessLevelCons.NORMAL) {
                    nearExc.put(next.getKey(), temp.getResultLevel());
                    nearExcTime.put(next.getKey(), temp.getCreateTime());
                    if (!result.containsKey(next.getKey())) {
                        result.put(next.getKey(), 0);
                    }
                    break;
                }
                if (result.containsKey(next.getKey())) {
                    Integer num = result.get(next.getKey());
                    result.put(next.getKey(), ++num);
                } else {
                    result.put(next.getKey(), 1);
                }
            }
        }
        resp.setNearExc(nearExc);
        resp.setNearExcTIme(nearExcTime);
        resp.setNearNormalCount(result);

        return resp;
    }

    /**
     * 获取分析结果
     * <p>
     * 总的分析过程如下
     * 判断近期最近的最严重的异常状态
     * 1.若轻度状态 ，需要至少两次的连续检测正常，才能转为正常
     * 2.若中度异常状态，需要连续三次轻度才能转检测轻度，连续两次正常转正常，
     * 3.若重度异常状态，需要连续五次中度或以下转中度，连续八次转轻度，十次转正常
     *
     * @param resp
     * @return
     */
    private UserAnalysisResp getAnalysisDetail(UserAnalysisResp resp, Map<String, List<AssessResult>> collect) {
        Map<String, Integer> analysisDetail = new HashMap<>();


        //最近一次的异常
        Map<String, Integer> nearExc = resp.getNearExc();

        Map<String, Integer> nearNormalCount = resp.getNearNormalCount();

        Map<String, Integer> nearTerrorBackCount = resp.getNearTerrorBackCount();
        //最近的最严重的异常等级
        Map<String, Integer> nearTerror = resp.getNearTerror();
        for (Map.Entry<String, Integer> next : nearTerror.entrySet()) {
            //正常的话直接返回正常
            if (AssessLevelCons.NORMAL.equals(next.getValue())) {
                analysisDetail.put(next.getKey(), AssessLevelCons.NORMAL);
                //轻度情况下,需要两次连续的正常状态，才能转正常
            } else if (AssessLevelCons.MILD.equals(next.getValue())) {
                if (nearNormalCount.get(next.getKey()) >= LevelVoteThresholdCons.MILD_TO_NORMAL) {
                    analysisDetail.put(next.getKey(), AssessLevelCons.NORMAL);
                } else {
                    analysisDetail.put(next.getKey(), AssessLevelCons.MILD);
                }
                //中度情况下，
            } else if (AssessLevelCons.MEDIUM.equals(next.getValue())) {
                //没有三次以上的轻度或正常，判定为中度
                if (nearTerrorBackCount.get(next.getKey()) < LevelVoteThresholdCons.MEDIUM_TO_MILD) {
                    analysisDetail.put(next.getKey(), AssessLevelCons.MEDIUM);
                    //若满足了三次以上的轻度或正常，还需判断，最近连续正常次数两次以上
                    //若大于,判定为正常,否则为轻度
                } else if (nearNormalCount.get(next.getKey()) < LevelVoteThresholdCons.MILD_TO_NORMAL) {
                    analysisDetail.put(next.getKey(), AssessLevelCons.MILD);
                } else {
                    analysisDetail.put(next.getKey(), AssessLevelCons.NORMAL);
                }
                //重度情况下
            } else {
                //需要五次以上才能转为中度
                if (nearTerrorBackCount.get(next.getKey()) < LevelVoteThresholdCons.SEVERE_TO_MEDIUM) {
                    analysisDetail.put(next.getKey(), AssessLevelCons.SEVERE);
                    //最近情况为中度的时候
                } else if (AssessLevelCons.MEDIUM.equals(nearExc.get(next.getKey()))) {
                    if (nearNormalCount.get(next.getKey()) > LevelVoteThresholdCons.SEVERE_TO_MEDIUM) {
                        analysisDetail.put(next.getKey(), AssessLevelCons.NORMAL);
                    } else if (nearNormalCount.get(next.getKey()) > LevelVoteThresholdCons.MEDIUM_TO_MILD) {
                        analysisDetail.put(next.getKey(), AssessLevelCons.MILD);
                    } else {
                        analysisDetail.put(next.getKey(), AssessLevelCons.MEDIUM);
                    }
                    //轻度
                } else {
                    List<AssessResult> assessResults = collect.get(next.getKey());
                    int temp = 0;
                    boolean flag = false;
                    for (AssessResult assessResult : assessResults) {
                        if (assessResult.getResultLevel().equals(AssessLevelCons.MILD)) {
                            flag = true;
                        }
                        if (flag) {
                            if (assessResult.getResultLevel() <= AssessLevelCons.MILD) {
                                temp++;
                            } else {
                                break;
                            }
                        }
                    }
                    Integer nearTerrorBackNum = resp.getNearTerrorBackCount().get(next.getKey());
                    Integer nearExcNum = resp.getNearExc().get(next.getKey());

                    //判定结果为重度的情况
                    if (nearTerrorBackNum < LevelVoteThresholdCons.SEVERE_TO_MEDIUM) {
                        analysisDetail.put(next.getKey(), AssessLevelCons.SEVERE);
                        //判定结果为中度的情况
                    } else if (nearTerrorBackNum < (LevelVoteThresholdCons.SEVERE_TO_MEDIUM + LevelVoteThresholdCons.MEDIUM_TO_MILD)) {
                        analysisDetail.put(next.getKey(), AssessLevelCons.MEDIUM);
                        //判定结果为正常的情况
                    } else if (nearExcNum >= LevelVoteThresholdCons.MILD_TO_NORMAL && temp > LevelVoteThresholdCons.MEDIUM_TO_MILD) {
                        analysisDetail.put(next.getKey(), AssessLevelCons.NORMAL);
                        //判定结果为轻度的情况
                    } else {
                        analysisDetail.put(next.getKey(), AssessLevelCons.MILD);
                    }
                }
            }
        }
        resp.setAnalysisDetails(analysisDetail);
        return resp;
    }

    private List<Integer> getMonthCount(List<AssessResult> list) {
        //将每月的总和相加
        Map<Integer, Integer> map = new HashMap<>(16);
        list.forEach(assessResult -> {
            if (map.containsKey(assessResult.getMonth())) {
                Integer count = map.get(assessResult.getMonth());
                map.put(assessResult.getMonth(), ++count);
            } else {
                map.put(assessResult.getMonth(), 1);
            }
        });
        //获取每月的测试总和
        List<Integer> everyMonthCount = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            if (map.containsKey(i)) {
                everyMonthCount.add(map.get(i));
            } else {
                everyMonthCount.add(0);
            }
        }
        return everyMonthCount;
    }

    private Long getUserId() {
        return ((User) StpUtil.getSession().get("user")).getId();
    }
}
