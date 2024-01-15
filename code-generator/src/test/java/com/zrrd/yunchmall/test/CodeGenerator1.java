package com.zrrd.yunchmall.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * 生成用户
 */
public class CodeGenerator1 {
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
                            .moduleName("user") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://云创商城项目/Code/mappers/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("ums_admin","ums_admin_login_log","ums_admin_permission_relation","ums_admin_role_relation","ums_growth_change_history","ums_integration_change_history","ums_integration_consume_setting","ums_member","ums_member_level","ums_member_login_log","ums_member_member_tag_relation","ums_member_product_category_relation","ums_member_receive_address","ums_member_rule_setting","ums_member_statistics_info","ums_member_tag","ums_member_task","ums_menu","ums_permission","ums_resource","ums_resource_category","ums_role","ums_role_menu_relation","ums_role_permission_relation","ums_role_resource_relation") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_", "ums_", "oms_", "pms_", "sms_", "cms_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}