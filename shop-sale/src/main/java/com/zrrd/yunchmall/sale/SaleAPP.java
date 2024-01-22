package com.zrrd.yunchmall.sale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zrrd.yunchmall.sale.mapper")
@EnableDiscoveryClient
@ComponentScan("com.zrrd.yunchmall")
public class SaleAPP {
    public static void main(String[] args) {
        SpringApplication.run(SaleAPP.class, args);
    }
}
