package com.zrrd.yunchmall.order.client;

import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shop-user")
public interface AdminServiceClient {

    @GetMapping("/info")
    public ResponseResult<Admin> info(@RequestHeader("Authorization") String token);

    @RequestMapping("/user/admin/testSleuth")
    public String testSleuth();
}
