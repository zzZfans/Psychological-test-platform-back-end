package com.cqjtu.mindassess.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangning
 *
 *  MybatisPlus配置类
 */
@Configuration
@MapperScan("com.cqjtu.mindassess.mapper")
public class MybatisPlusConfig {
}
