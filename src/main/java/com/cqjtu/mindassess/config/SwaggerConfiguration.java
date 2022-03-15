package com.cqjtu.mindassess.config;

import com.cqjtu.mindassess.annotation.NoIncludeSwagger;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * 注册一个Docket对象，docket是Swagger的全局配置对象
     * @return
     */
    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        // ApiInfo 接口文档的描述信息
        ApiInfo apiInfo = new ApiInfoBuilder()
                .contact(new Contact(
                        "计科毕设开发接口文档",               // 文档发布者名称
                        "no",                                   // 文档发布者(组织)的网站地址
                        "amosdzhn@163.com"))                    // 文档发布者的邮箱
                .title("mind-assess 接口文档")
                .description("mind-assess后端接口文档")
                .version("1.0.0")
                .build();


        docket.apiInfo(apiInfo);
        docket = docket.select()
                .apis(
                        Predicates.and(
                        Predicates.not(withMethodAnnotation(NoIncludeSwagger.class)),    //哪个注解作用在方法上，该方法不生成文档
                        Predicates.not(withClassAnnotation(NoIncludeSwagger.class)),
                        RequestHandlerSelectors.basePackage("com.cqjtu.mindassess.controller")  //设置扫描哪个包中的注解
                ))
                .paths(
                        Predicates.or(
                                PathSelectors.regex("/.*"),   // 使用正则约束 请求url 来决定是否生成文档
                                PathSelectors.regex("/swagger2/.*")
                        )
                )
                .build();

        return docket;
    }

}
