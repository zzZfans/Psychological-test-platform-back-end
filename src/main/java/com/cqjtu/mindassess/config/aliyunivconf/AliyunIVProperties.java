package com.cqjtu.mindassess.config.aliyunivconf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 阿里云智能语音服务配置类
 * @Author: amosdzhn
 * @CreateTime: 2022/5/7 15:52
 */
@ConfigurationProperties(prefix = "aliyun-intelligent-voice")
@Component
@Data
public class AliyunIVProperties {

    private String accessKeyId;
    private String accessKeySecret;
    private String appKey;
}
