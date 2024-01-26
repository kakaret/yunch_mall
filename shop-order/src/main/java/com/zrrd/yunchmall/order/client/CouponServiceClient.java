package com.zrrd.yunchmall.order.client;

import com.zrrd.yunchmall.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shop-sale")
public interface CouponServiceClient {
    @RequestMapping("/coupon/freeCoupon")
    public ResponseResult freeCoupon(@RequestParam Long orderId);
}
