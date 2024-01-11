package com.zrrd.yunchmall.order.service;

import com.zrrd.yunchmall.pojo.ProductTmp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//模拟一个商品服务的客户端
@FeignClient(value = "shop-product")
public interface ProductServiceClient {
//    当调用了这个方法 就等于执行了http://shop-product/product/{pid}
    @GetMapping("/product/{pid}")
    public ProductTmp detail(@PathVariable("pid") int pid);
}
