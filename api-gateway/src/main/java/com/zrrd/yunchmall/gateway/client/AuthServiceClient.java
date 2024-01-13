package com.zrrd.yunchmall.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shop-user")
public interface AuthServiceClient {
    @RequestMapping("user/auth")
    public Object auth(String token);
}
