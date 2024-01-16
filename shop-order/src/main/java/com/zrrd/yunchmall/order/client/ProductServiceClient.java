package com.zrrd.yunchmall.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shop-product")
public interface ProductServiceClient {
    @RequestMapping("/product/testSleuth")
    public String testSleuth();
}
