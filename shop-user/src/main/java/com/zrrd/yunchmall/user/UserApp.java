package com.zrrd.yunchmall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zrrd.yunchmall.user.mapper")
//开启服务注册及发现
@EnableDiscoveryClient
@ComponentScan("com.zrrd.yunchmall")
@EnableCaching //开启当前应用的缓存开关
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
    }
}
