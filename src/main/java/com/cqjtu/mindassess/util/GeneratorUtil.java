package com.cqjtu.mindassess.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;


/**
 * @author: author
 *
 * 代码生成器
 * @date: 2022/3/10
 */
public abstract class GeneratorUtil {


    /**
     * 注意
     *          使用代码生成器时候修改下列地方：
     *              1.outputDir 路径参数
     *              2.pathInfo 中的路径
     *              3.include 中的数据库表名
     */

    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://www.amosdzhn.space:10000/db_dxsxl", "auser", "GOGO123..")
                .globalConfig(builder -> {
                    builder.author("auther") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .disableOpenDir()
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("F:\\awesome\\mind-assess\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.cqjtu.mindassess") // 设置父包名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "F:\\awesome\\mind-assess\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("role_permission") // 设置需要生成的表名
                            .entityBuilder()   // 实体策略配置
                            .idType(IdType.AUTO)
                            .enableLombok()
                            .controllerBuilder()  // controller策略配置
                            .enableRestStyle();
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}