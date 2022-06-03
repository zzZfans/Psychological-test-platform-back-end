package com.cqjtu.mindassess.task;

import com.cqjtu.mindassess.entity.AutoMessage;
import com.cqjtu.mindassess.entity.PushRecord;
import com.cqjtu.mindassess.mapper.AssessResultMapper;
import com.cqjtu.mindassess.pojo.resp.assess.UserAssessBo;
import com.cqjtu.mindassess.service.IAutoMessageService;
import com.cqjtu.mindassess.service.IPushRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author zhangzhencheng
 */
@Service
@EnableScheduling
public class PushAdviceTask {
    @Autowired
    private AssessResultMapper resultMapper;
    @Autowired
    private IPushRecordService pushRecordService;
    @Autowired
    private IAutoMessageService autoMessageService;

    //消息缓存
    private List<AutoMessage> autoMessages = null;

    /**
     * 每天十二点进行一次消息推送
     */
    @Async
    @Scheduled(cron = "0 0 12 * * ?")
    public void pushAdvice() {
        List<PushRecord> pushRecords = new ArrayList<>();
        //用心理问题记录的用户以及测试类型列表
        List<UserAssessBo> proUserList = resultMapper.getProUserList();
        PushRecord push;
        List<AutoMessage> collect;
        AutoMessage autoMessage1;
        autoMessages = autoMessageService.list();
        for (UserAssessBo assess : proUserList) {
            Random rand = new Random();
            push = new PushRecord();
            collect = autoMessages.stream()
                    .filter(autoMessage -> autoMessage.getType().equals(assess.getAssessType())
                            || "all".equals(autoMessage.getType())).collect(Collectors.toList());
            if (collect.size() > 0) {
                //随机获取对应类型的一条消息
                int index = rand.nextInt(Math.max((collect.size() - 1), 1));
                autoMessage1 = collect.get(Math.min(collect.size(), index));

                push.setTitle(autoMessage1.getTitle());
                push.setMessage(autoMessage1.getMessage());
                // 0表示系统推送
                push.setPusherId(0L);
                push.setReceiverId(assess.getUserId());
                push.setStatus(0);
                pushRecords.add(push);
            }
        }
        pushRecordService.saveBatch(pushRecords);
        System.out.println("定时任务执行完毕。。。" + Thread.currentThread().getName());
    }
}
