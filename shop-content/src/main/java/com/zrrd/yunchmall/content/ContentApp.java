package com.zrrd.yunchmall.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zrrd.yunchmall.content.mapper")
@EnableDiscoveryClient
@ComponentScan(value = "com.zrrd.yunchmall")
public class ContentApp {
    public static void main(String[] args) {
        SpringApplication.run(ContentApp.class, args);
    }
}
