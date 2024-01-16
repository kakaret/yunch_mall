package com.zrrd.yunchmall.order.controller;

import com.zrrd.yunchmall.order.client.AdminServiceClient;
import com.zrrd.yunchmall.order.client.ProductServiceClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单服务接口")
public class OrderController {

    @Autowired
    AdminServiceClient adminService;
    @Autowired
    ProductServiceClient productService;

    @RequestMapping("/testSleuth")
    @ApiOperation(value = "测试链路追踪", httpMethod = "GET")
    public String testSleuth() {
//        模拟分别调用商品和管理员服务
        adminService.testSleuth();
        productService.testSleuth();
        return "订单：测试链路追踪";
    }
}
