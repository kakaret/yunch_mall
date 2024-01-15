package com.zrrd.yunchmall.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shop-user")
public interface AuthServiceClient {
    @RequestMapping("user/auth")
    public Object auth(@RequestParam("token") String token);
}
