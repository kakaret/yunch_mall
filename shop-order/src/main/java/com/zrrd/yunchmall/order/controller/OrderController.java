package com.zrrd.yunchmall.order.controller;

import com.zrrd.yunchmall.order.service.OrderServiceImpl;
import com.zrrd.yunchmall.pojo.OrderTmp;
import com.zrrd.yunchmall.pojo.ProductTmp;
import com.zrrd.yunchmall.pojo.UserTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
//    private final String productServiceUrl = "http://localhost:8071"; //不易维护

    private String productServiceUrl;
    private String userServiceUrl;

    static int i = 0;
    static int j = 0;
    /**
     * 保存了需要调用的远程客户端的信息，这部分信息是从Nacos注册中心取到的
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/submit")
    public Map submitOrder(int uid, int pid, int count) {
////        一共有几个名字叫shop-product的同名服务
//        final int N = discoveryClient.getInstances("shop-product").size();
        final int N1 = discoveryClient.getInstances("shop-user").size();
////        指定服务名称（shop-product） 获取服务实例（ServiceInstance）
//        i = (i + 1) % N;
        j = (j + 1) % N1;
//        ServiceInstance instance = discoveryClient.getInstances("shop-product").get(i);
        ServiceInstance instance2 = discoveryClient.getInstances("shop-user").get(j);
////        获取服务实例（ServiceInstance）的host和port，拼接成http请求的url
////                                       127.0.0.1                      8071
//        productServiceUrl = "http://" + instance.getHost() + ":" + instance.getPort();
        userServiceUrl = "http://" + instance2.getHost() + ":" + instance2.getPort();
        String uri = "/product/" + pid;
        String uuri = "/user/" + uid;
//        调用商品服务 查询商品信息

        productServiceUrl = "http://" + "shop-product";
//        userServiceUrl = "http://" + "shop-user";
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
            UserTmp userInfo = restTemplate.getForObject(userServiceUrl + uuri, UserTmp.class);


            OrderTmp orderTmp = new OrderTmp(null, uid, userInfo.getUsername(), pid, productInfo.getPname(), productInfo.getPprice(), count);
            orderService.save(orderTmp); //保存订单数据
//            下单成功
            Map ret = new HashMap();
            ret.put("用户昵称", userInfo.getUsername());
            ret.put("商品信息", productInfo);
            ret.put("下单数量", count);
            ret.put("Message", "下单成功!");
            return ret;
        }
    }
}
