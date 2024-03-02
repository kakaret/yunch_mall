package com.zrrd.yunchmall.order;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

@SpringBootApplication
@MapperScan("com.zrrd.yunchmall.order.mapper")
//开启服务注册及发现
@EnableDiscoveryClient
//启用Feign客户端 如果不写这个注解 可能导致Feign客户端失效
@EnableFeignClients
@ComponentScan(value = "com.zrrd.yunchmall")
public class OrderApp {
//    先在订单服务的容器里注入一个RestTemplate的Bean
//    RestTemplate是使用模板模式创建的一个能够发起http连接的组件，它能将http复杂的连接过程做了封装，可以使用它方便的创建一个http客户端
//    在当前spring容器内注入一个RestTemplate实例
    @Bean
//    启用负载均衡器（由Ribbon组件提供支持）
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
}

/**
 * 这是一个什么项目
 * 项目的架构和使用的开发技术
 * 包含了那些服务（模块） 你负责了其中的哪些部分
 */