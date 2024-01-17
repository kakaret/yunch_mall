package com.zrrd.yunchmall.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//方式二：自动刷新属性的值
@RefreshScope
public class ConfigTestController {

//    方式一：硬编码方式获取上下文环境中的属性最新值
    @Autowired
    private ConfigurableApplicationContext context;

    @Value("${author.name}")
    private String authorName;

    @RequestMapping("/configTest1")
    public String getAuthProperty() {
        return context.getEnvironment().getProperty("author.name");
    }

    @RequestMapping("/configTest2")
    public String getAuthorProperty2() {
        return authorName;
    }
}
