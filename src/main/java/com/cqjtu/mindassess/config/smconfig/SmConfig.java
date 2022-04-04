package com.cqjtu.mindassess.config.smconfig;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangning
 *
 *  短信客户端配置
 */
@Configuration
public class SmConfig {

    @Bean
    public SmsClient smsClient(@Qualifier("tencentSmProperties") TencentSmProperties tencentSmProperties){
        Credential cred = new Credential(
                tencentSmProperties.getSecretId(),
                tencentSmProperties.getSecretKey()
        );
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(tencentSmProperties.getEndpoint());

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        SmsClient client = new SmsClient(
                cred,
                tencentSmProperties.getRegion(),
                clientProfile);
        return client;
    }

}
