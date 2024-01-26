package com.zrrd.yunchmall.order.client;

import com.zrrd.yunchmall.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient("shop-product")
public interface ProductServiceClient {

    @PostMapping("/product/stock/free")
    public ResponseResult freeStock(@RequestBody List<Map<String, Object>> params);

    @RequestMapping("/product/testSleuth")
    public String testSleuth();
}
