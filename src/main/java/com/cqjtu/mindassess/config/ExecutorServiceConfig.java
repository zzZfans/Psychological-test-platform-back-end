package com.cqjtu.mindassess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class ExecutorServiceConfig {

    /**
     * 核心线程数量
     */
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    /**
     * 工作队列数
     */
    private static final int QUEUE_CAPACITY = 200;
    /**
     * 线程池名前缀
     */
    private static final String THREAD_NAME_PREFIX = "Async-Service-";

    @Bean
    public ThreadPoolTaskExecutor get() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

}