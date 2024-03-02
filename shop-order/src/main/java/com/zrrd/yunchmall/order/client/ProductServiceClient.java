package com.zrrd.yunchmall.order.client;

import com.zrrd.yunchmall.product.entity.Product;
import com.zrrd.yunchmall.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("shop-product")
public interface ProductServiceClient {

    @PostMapping("/product/stock/free")
    public ResponseResult freeStock(@RequestBody List<Map<String, Object>> params);

    @RequestMapping("/product/testSleuth")
    public String testSleuth();

    @PostMapping("/product/subStock")
    public ResponseResult subStock(@RequestParam("pid") Long pid, @RequestParam("num") Integer num);

    @GetMapping("/product/{id}")
    public ResponseResult<Product> detail(@PathVariable("id") long id);
}
