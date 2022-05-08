package com.cqjtu.mindassess.config.aliyunivconf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 阿里云智能语音服务配置类
 * @Author: amosdzhn
 * @CreateTime: 2022/5/7 15:20
 */
@Configuration
public class AliyunIntelligentVoiceConfig {

    @Bean
    public AliyunIVClient aliyunIVClient(AliyunIVProperties properties){
        AliyunIVClient aliyunIVClient = new AliyunIVClient(properties.getAccessKeyId(), properties.getAccessKeySecret());
        aliyunIVClient.setAppKey(properties.getAppKey());
        return aliyunIVClient;
    }
}
