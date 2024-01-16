package com.zrrd.yunchmall.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("/testSleuth")
    public String testSleuth(){
        return "商品：测试链路追踪";
    }
}
