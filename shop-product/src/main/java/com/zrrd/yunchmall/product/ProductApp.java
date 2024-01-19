package com.zrrd.yunchmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.zrrd.yunchmall.product.mapper")
@EnableDiscoveryClient
@ComponentScan("com.zrrd.yunchmall")
public class ProductApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class, args);
    }
}
