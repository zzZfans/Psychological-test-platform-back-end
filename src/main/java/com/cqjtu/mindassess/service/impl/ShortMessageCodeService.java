package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.config.smconfig.TencentSmProperties;
import com.cqjtu.mindassess.exception.SystemErrorException;
import com.cqjtu.mindassess.service.IShortMessageCodeService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanging
 *
 *  短信验证码实现类
 */

@Deprecated
@Slf4j
@Service
public class ShortMessageCodeService implements IShortMessageCodeService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    SmsClient smsClient;
    @Autowired
    TencentSmProperties tencentSmProperties;

    // 过期时间
    private static final long KEY_EXPIRED_TIME = 10;

    /**
     * 验证码长度
     */
    private static final int CODE_LENGTH = 6;

    @Override
    public long requestSmCode(String phoneNumber,String scenes) {
        Random random = new Random(System.nanoTime());
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }
        String key = phoneNumber + ":" + scenes + ":" + code;
        log.info("生成的key: {}",key);
        stringRedisTemplate.opsForValue().set(key,"",KEY_EXPIRED_TIME, TimeUnit.MINUTES);
        //调用短信API，发送短信
        SendSmsRequest req = new SendSmsRequest();
        req.setSmsSdkAppid(tencentSmProperties.getSdkAppid());
        req.setSign(tencentSmProperties.getSign());
        req.setTemplateID(tencentSmProperties.getTemplateIds()[0]);
        String[] templateParams = {code.toString(),String.valueOf(KEY_EXPIRED_TIME)};
        req.setTemplateParamSet(templateParams);
        String[] phoneNumberSet = {"+86"+phoneNumber};
        req.setPhoneNumberSet(phoneNumberSet);
        try {
            smsClient.SendSms(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            throw new SystemErrorException("短信服务异常");
        }
        return KEY_EXPIRED_TIME * 60;
    }

    @Override
    public boolean confirmSmCode(String phoneNumber, String code, String scenes) {
        // code=1111 白名单
        //TODO  项目完成后删除
        if(code.equals("1111")){
            return true;
        }
        String key = phoneNumber + ":" + scenes + ":" + code;

        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfPresent(key, "", 1, TimeUnit.SECONDS));
    }
}
