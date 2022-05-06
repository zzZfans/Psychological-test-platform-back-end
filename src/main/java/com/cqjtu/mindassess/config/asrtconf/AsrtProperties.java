package com.cqjtu.mindassess.config.asrtconf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangning
 */
@ConfigurationProperties(prefix = "asrt")
@Data
@Component("asrtProperties")
public class AsrtProperties {

    /**
     * ASRT Server 主机
     */
    private String host;

    /**
     * ASRT Server 端口号
     */
    private String port;

    /**
     * ASRT Server HTTP协议
     */
    private String protocol;
}
