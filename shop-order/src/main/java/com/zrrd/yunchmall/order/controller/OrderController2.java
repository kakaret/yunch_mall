package com.zrrd.yunchmall.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zrrd.yunchmall.order.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/order")
public class OrderController2 {

    @Autowired
    private OrderServiceImpl orderService;
    @RequestMapping("/m1")
    public String m1() throws InterruptedException {
//        Random random = new Random();
//        if(random.nextInt(10000) % 4 == 0) { //25%
//            throw new RuntimeException("模拟程序出错了");
//        }
//        if(random.nextBoolean()) {
//            Thread.sleep(500);
//        }
        return orderService.message(1);
    }
    @RequestMapping("/m2")
    public String m2() {
        return orderService.message(2);
    }
    @RequestMapping("/m3")
//    热点规则需要使用@SentinelResource定义
    @SentinelResource("M3")
    public String m3(String name, int age) {
        return "姓名" + name + "年龄" + age;
//        return orderService.message(3);
    }
}
