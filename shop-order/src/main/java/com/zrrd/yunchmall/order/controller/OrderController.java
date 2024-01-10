package com.zrrd.yunchmall.order.controller;

import com.zrrd.yunchmall.order.service.OrderServiceImpl;
import com.zrrd.yunchmall.pojo.OrderTmp;
import com.zrrd.yunchmall.pojo.ProductTmp;
import com.zrrd.yunchmall.pojo.UserTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private RestTemplate restTemplate; //http客户端
    private final String productServiceUrl = "http://localhost:8071";
    private final String userServiceUrl = "http://localhost:8061";

    @RequestMapping("/submit")
    public Map submitOrder(int uid, int pid, int count) {
        String uri = "/product/" + pid;
        String uri2 = "/user/" + uid;
//        调用商品服务 查询商品信息
        ProductTmp productInfo = restTemplate.getForObject(productServiceUrl + uri, ProductTmp.class);
//        判断库存是否充足
        if (productInfo.getStock() < count) {
            Map ret = new HashMap();
            ret.put("商品名", productInfo.getPname());
            ret.put("Message", "库存不足!");
            return ret;
        } else {
//            扣库存
//            查询uid对应的用户信息
            UserTmp userInfo = restTemplate.getForObject(userServiceUrl + uri2, UserTmp.class);

            assert userInfo != null;

            OrderTmp orderTmp = new OrderTmp(null, uid, userInfo.getNickName(), pid, productInfo.getPname(), productInfo.getPprice(), count);
            orderService.save(orderTmp); //保存订单数据
//            下单成功
            Map ret = new HashMap();
            ret.put("用户昵称", userInfo.getNickName());
            ret.put("商品信息", productInfo);
            ret.put("下单数量", count);
            ret.put("Message", "下单成功!");
            return ret;
        }
    }
}
