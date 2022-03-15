package com.cqjtu.mindassess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author author
 */
@SpringBootApplication
public class MindAssessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindAssessApplication.class, args);
        System.out.println("本地接口文档地址: http://localhost:8080/swagger-ui.html");
    }

}
