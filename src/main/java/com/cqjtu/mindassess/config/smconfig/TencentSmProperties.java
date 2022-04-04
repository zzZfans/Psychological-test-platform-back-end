package com.cqjtu.mindassess.config.smconfig;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangning
 *
 *      腾讯云短信配置类
 */
@ConfigurationProperties(prefix = "short-message.tencent-sm")
@Component
@Data
@ToString
public class TencentSmProperties {
    private String endpoint;
    private String region;
    private String secretId;
    private String secretKey;
    private String sdkAppid;
    private String sign;
    private String[] templateIds;
}
