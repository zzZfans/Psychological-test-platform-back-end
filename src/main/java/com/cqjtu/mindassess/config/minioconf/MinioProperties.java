package com.cqjtu.mindassess.config.minioconf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "minio")
@Component
@Data
public class MinioProperties {
    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String accessUrlPrefix;
}
