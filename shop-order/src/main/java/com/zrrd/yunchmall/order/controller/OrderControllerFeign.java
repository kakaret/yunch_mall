package com.zrrd.yunchmall.order.controller;

import com.zrrd.yunchmall.order.service.OrderServiceImpl;
import com.zrrd.yunchmall.order.service.client.ProductServiceClient;
import com.zrrd.yunchmall.order.service.client.UserServiceClient;
import com.zrrd.yunchmall.pojo.OrderTmp;
import com.zrrd.yunchmall.pojo.ProductTmp;
import com.zrrd.yunchmall.pojo.UserTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order-feign")
public class OrderControllerFeign {
    @Autowired
    private OrderServiceImpl orderService; //本地的OrderService的依赖注入
    @Autowired
    private ProductServiceClient productService; //远程服务的ProductService的依赖注入
    @Autowired
    private UserServiceClient userService;
    @RequestMapping("/submit")
    public Map submitOrder(int uid, int pid, int count) {

        ProductTmp productInfo = productService.detail(pid);
        //判断库存是否充足
        if (productInfo.getStock() < count) {//库存不足
            Map ret = new HashMap();
            ret.put("商品名：", productInfo.getPname());
            ret.put("Message", "库存不足！");
            return ret;
        } else {
            UserTmp userInfo = userService.userInfo(uid);
            //生成订单数据
            OrderTmp order = new OrderTmp(null, uid, userInfo.getUsername(), pid, productInfo.getPname(),
                    productInfo.getPprice(), count);
            orderService.save(order);//保存订单数据
            //返回下单成功
            Map ret = new HashMap();
            ret.put("用户信息：", userInfo.getUsername());
            ret.put("商品信息：", productInfo);
            ret.put("下单数量：", count);
            ret.put("Message", "下单成功！");
            return ret;
        }
    }
}
