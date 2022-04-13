package com.cqjtu.mindassess.config;

import com.cqjtu.mindassess.interceptor.AccessTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangning
 */
@Deprecated
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public AccessTokenInterceptor accessTokenInterceptor() {
        return new AccessTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/doc.html/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/sm/code/**")
                .excludePathPatterns("/captcha");
    }
}
