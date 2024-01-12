package com.zrrd.yunchmall.order.service.client;

import com.zrrd.yunchmall.pojo.UserTmp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shop-user")
public interface UserServiceClient {
    @RequestMapping("/user/{uid}")
    public UserTmp userInfo(@PathVariable("uid") int uid);
}
