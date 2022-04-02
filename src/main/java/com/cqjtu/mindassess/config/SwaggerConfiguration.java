package com.cqjtu.mindassess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author zhangning
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

    /**
     * 注册一个Docket对象，docket是Swagger的全局配置对象
     * @return
     */
    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("大学生心理测试平台 RESTful APIs")
                        .termsOfServiceUrl("")
                        .contact("黎小宏,张真逞,曾凡深,张宁")
                        .version("v:1.0.0")
                        .build())
                // 分组名称
                .groupName("default")
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.cqjtu.mindassess.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}
