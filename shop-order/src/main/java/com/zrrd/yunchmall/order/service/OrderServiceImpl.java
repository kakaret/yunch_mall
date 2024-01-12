package com.zrrd.yunchmall.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.order.mapper.OrderMapper;
import com.zrrd.yunchmall.pojo.OrderTmp;
import com.zrrd.yunchmall.pojo.ProductTmp;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderTmp> implements IService<OrderTmp> {
// @SentinelResource可以用于将一个普通的类或方法定义成Sentinel的资源
    @SentinelResource("MSR01")
    public String message(int i) {
        return "message" + i;
    }
}

