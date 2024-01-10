package com.zrrd.yunchmall.product.controller;

import com.zrrd.yunchmall.pojo.ProductTmp;
import com.zrrd.yunchmall.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

   @GetMapping("/{pid}")
    public ProductTmp detail(@PathVariable("pid") int pid) {
        return productService.getById(pid);
    }
}
