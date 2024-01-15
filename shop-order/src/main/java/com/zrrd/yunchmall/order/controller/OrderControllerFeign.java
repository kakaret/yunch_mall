package com.zrrd.yunchmall.order.controller;

import com.zrrd.yunchmall.order.service.OrderServiceImpl;
import com.zrrd.yunchmall.order.service.client.ProductServiceClient;
import com.zrrd.yunchmall.order.service.client.UserServiceClient;
import com.zrrd.yunchmall.pojo.OrderTmp;
import com.zrrd.yunchmall.pojo.ProductTmp;
import com.zrrd.yunchmall.pojo.UserTmp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order-feign")
@Api(tags = "订单服务接口")
public class OrderControllerFeign {
    @Autowired
    private OrderServiceImpl orderService; //本地的OrderService的依赖注入
    @Autowired
    private ProductServiceClient productService; //远程服务的ProductService的依赖注入
    @Autowired
    private UserServiceClient userService;

    @RequestMapping("/submit")
    @ApiOperation(value = "提交订单", httpMethod = "POST")
    public Map submitOrder(@ApiParam(required = true) @RequestBody OrderTmp orderTmp) {

        ProductTmp productInfo = productService.detail(orderTmp.getPid());
        //判断库存是否充足
        if (productInfo.getStock() < orderTmp.getNumber()) {//库存不足
            Map ret = new HashMap();
            ret.put("商品名：", productInfo.getPname());
            ret.put("Message", "库存不足！");
            return ret;
        } else {
            UserTmp userInfo = userService.userInfo(orderTmp.getUid());
            //生成订单数据
            OrderTmp order = new OrderTmp(null, orderTmp.getUid(), userInfo.getUsername(), orderTmp.getPid(), productInfo.getPname(),
                    productInfo.getPprice(), orderTmp.getNumber());
            orderService.save(order);//保存订单数据
            //返回下单成功
            Map ret = new HashMap();
            ret.put("用户信息：", userInfo.getUsername());
            ret.put("商品信息：", productInfo);
            ret.put("下单数量：", orderTmp.getNumber());
            ret.put("Message", "下单成功！");
            return ret;
        }
    }
}
