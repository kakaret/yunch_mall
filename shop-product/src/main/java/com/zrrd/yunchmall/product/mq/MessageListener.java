package com.zrrd.yunchmall.product.mq;

import com.zrrd.yunchmall.config.RabbitConfig;
import com.zrrd.yunchmall.product.entity.Product;
import com.zrrd.yunchmall.product.service.IProductService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
public class MessageListener {
    @Autowired
    private IProductService productService;

    @RabbitHandler
    public void process(Map map) {
        try {
            long productId = (long) map.get("productId");
//        使用商品Id查询商品对象信息


            Product product = productService.getById(productId);
            int returnCount = (int) map.get("productCount");
            product.setStock(product.getStock() + returnCount);
            productService.updateById(product);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






