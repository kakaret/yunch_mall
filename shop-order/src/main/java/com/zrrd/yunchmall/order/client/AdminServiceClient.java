package com.zrrd.yunchmall.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shop-user")
public interface AdminServiceClient {
    @RequestMapping("/user/admin/testSleuth")
    public String testSleuth();
}
