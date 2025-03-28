package com.zrrd.yunchmall.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * 生成用户
 */
public class CodeGenerator2 {
    //根据我们数据源的真实信息定义：URL，username，password
    //使用虚拟机运行mysql的话，IP也要改
    String url = "jdbc:mysql://localhost:3306/yunch_mall?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
    String username = "root";
    String password = "root";//根据自己的mysql实际密码进行修改

    @Test
    public void generateCode() {
        //
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("JGX") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E://云创商城项目/Code/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zrrd.yunchmall") // 设置父包名
                            .moduleName("order") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://云创商城项目/Code/mappers/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("oms_cart_item","oms_company_address","oms_order","oms_order_item","oms_order_operate_history","oms_order_return_apply","oms_order_return_reason","oms_order_setting") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_", "ums_", "oms_", "pms_", "sms_", "cms_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}