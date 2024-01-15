package com.zrrd.yunchmall.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * 生成用户
 */
public class CodeGenerator3 {
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
                            .moduleName("product") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://云创商城项目/Code/mappers/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("pms_album","pms_album_pic","pms_brand","pms_comment","pms_comment_replay","pms_feight_template","pms_member_price","pms_product","pms_product_attribute","pms_product_attribute_category","pms_product_attribute_value","pms_product_category","pms_product_category_attribute_relation","pms_product_full_reduction","pms_product_ladder","pms_product_operate_log","pms_product_vertify_record","pms_sku_stock") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_", "ums_", "oms_", "pms_", "sms_", "cms_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}