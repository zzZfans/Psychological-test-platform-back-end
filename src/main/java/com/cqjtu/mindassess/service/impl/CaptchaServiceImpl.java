package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.config.smconfig.TencentSmProperties;
import com.cqjtu.mindassess.enums.CaptchaSceneEnum;
import com.cqjtu.mindassess.enums.CaptchaTypeEnum;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.exception.SystemErrorException;
import com.cqjtu.mindassess.service.ICaptchaService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangning
 * 验证码服务实现类
 */
@Service
public class CaptchaServiceImpl implements ICaptchaService {

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    SmsClient smsClient;
    @Resource
    TencentSmProperties tencentSmProperties;

    /**
     * 默认验证码默认长度
     */
    private static final int DEFAULT_CODE_LENGTH = 6;

    /**
     * 默认验证码过期时间(单位分钟)
     */
    private static final int DEFAULT_CODE_EXPIRATION_TIME = 10;

    /**
     * 生成一个验证码,默认6位
     *
     * @return 返回生成的验证码字符串
     */
    private String createCode() {
        Random random = new Random(System.nanoTime());
        StringBuilder code = new StringBuilder(DEFAULT_CODE_LENGTH);
        for (int i = 0; i < DEFAULT_CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    /**
     * 生成key
     *
     * @param recipient    -接受方
     * @param code         - 验证码
     * @param captchaScene - 场景
     * @return key
     */
    private String createKey(String recipient, String code, CaptchaSceneEnum captchaScene) {
        return recipient + ":" + captchaScene.scene + ":" + code;
    }

    /**
     * 发送短信验证码
     *
     * @param phone   电话号码
     * @param context 内容
     * @return 过期时间
     */
    private long sendSmCode(String phone, String context, CaptchaSceneEnum sceneEnum) {
        SendSmsRequest req = new SendSmsRequest();
        req.setSmsSdkAppid(tencentSmProperties.getSdkAppid());
        req.setSign(tencentSmProperties.getSign());
        // 短信模板内容内容参数
        String[] templateParams;
        if (CaptchaSceneEnum.REGISTER.scene.equals(sceneEnum.scene)) {
            req.setTemplateID(tencentSmProperties.getTemplateIds()[0]);
            templateParams = new String[]{context, String.valueOf(DEFAULT_CODE_EXPIRATION_TIME)};
        } else if (CaptchaSceneEnum.LOGIN.scene.equals(sceneEnum.scene)) {
            req.setTemplateID(tencentSmProperties.getTemplateIds()[1]);
            templateParams = new String[]{context};
        } else {
            throw new SystemErrorException("系统不支持当前类型的短信模板");
        }
        req.setTemplateParamSet(templateParams);
        //设置接受者
        String[] phoneNumberSet = {
                "+86" + phone};
        req.setPhoneNumberSet(phoneNumberSet);
        try {
            smsClient.SendSms(req);
        } catch (TencentCloudSDKException tce) {
            tce.printStackTrace();
            throw new SystemErrorException("短信服务异常");
        }
        return DEFAULT_CODE_EXPIRATION_TIME * 60;
    }


    /**
     * 发端邮箱验证码
     *
     * @param emailAddress 电子邮箱
     * @param context      邮件内容
     * @return 过期时间
     */
    private long sendEmailCode(String emailAddress, String context) {
        return -1;
    }

    @Override
    public long requestCode(String recipient, CaptchaTypeEnum captchaType, CaptchaSceneEnum captchaScene) {
        String code = createCode();
        String key = createKey(recipient, code, captchaScene);
        stringRedisTemplate.opsForValue().set(key, "", DEFAULT_CODE_EXPIRATION_TIME, TimeUnit.MINUTES);
        long expireTime = DEFAULT_CODE_EXPIRATION_TIME * 60;
        if (CaptchaTypeEnum.MOBILE.equals(captchaType)) {
            //发送短信验证码
            sendSmCode(recipient, code, captchaScene);
        } else if (CaptchaTypeEnum.EMAIL.equals(captchaType)) {
            //发送邮件验证码
            sendEmailCode(recipient, code);
        } else {
            throw new BusinessException("不支持的验证码类型");
        }
        return expireTime;
    }

    @Override
    public boolean confirmCode(String recipient, String code, CaptchaTypeEnum captchaType, CaptchaSceneEnum captchaScene) {
        //TODO
        //白名单 1111,完毕后删除
        if (code.equals("1111")) {
            return true;
        }
        String key = createKey(recipient, code, captchaScene);
        Boolean exist = stringRedisTemplate.opsForValue().setIfPresent(key, "", 1, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(exist);
    }
}
