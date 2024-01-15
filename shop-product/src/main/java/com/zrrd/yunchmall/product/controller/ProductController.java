package com.zrrd.yunchmall.product.controller;

import com.zrrd.yunchmall.pojo.ProductTmp;
import com.zrrd.yunchmall.product.service.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Api(tags = "商品服务接口")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

   @GetMapping("/{pid}")
   @ApiOperation(value = "查询商品详情", httpMethod = "GET")
    public ProductTmp detail(@ApiParam(value = "商品Id", required = true) @PathVariable("pid") int pid) {
//       try {
//           Thread.sleep(100); //让每次请求线程都睡眠0.1s
//       } catch (InterruptedException e) {
//           throw new RuntimeException(e);
//       }
       return productService.getById(pid);
    }
}
