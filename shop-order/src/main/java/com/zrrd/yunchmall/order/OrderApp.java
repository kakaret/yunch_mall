package com.zrrd.yunchmall.order;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

@SpringBootApplication
@MapperScan("com.zrrd.yunchmall.order.mapper")
//开启服务注册及发现
@EnableDiscoveryClient
public class OrderApp {
//    先在订单服务的容器里注入一个RestTemplate的Bean
//    RestTemplate是使用模板模式创建的一个能够发起http连接的组件，它能将http复杂的连接过程做了封装，可以使用它方便的创建一个http客户端
//    在当前spring容器内注入一个RestTemplate实例
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);

    }
}
