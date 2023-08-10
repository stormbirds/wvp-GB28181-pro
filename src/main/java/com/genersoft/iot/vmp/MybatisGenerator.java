package com.genersoft.iot.vmp;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @ Description cn.stormbirds.skyeye
 * @ Author StormBirds
 * @ Email xbaojun@gmail.com
 * @ Date 2021/12/12 1:32
 */
public class MybatisGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/wvp",
                "root",
                "root123456")
                .globalConfig(builder -> {
                    builder.author("stormbirds") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.TIME_PACK)
                            .outputDir("E:\\dev\\wvp-GB28181-pro"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.genersoft.iot.vmp") // 设置父包名
                            .moduleName("wvp-pro") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\dev\\wvp-GB28181-pro\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation();
                    builder.controllerBuilder()
                            .enableRestStyle();
                    builder.likeTable(new LikeTable("t_")) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
