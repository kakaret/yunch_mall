package com.zrrd.yunchmall.order.service.client.fallback;

import com.zrrd.yunchmall.order.service.client.ProductServiceClient;
import com.zrrd.yunchmall.pojo.ProductTmp;
import org.springframework.stereotype.Service;


//当所有的商品服务状态都出现了 异常状况 就回调这个类当中的方法
@Service
public class ProductServiceClientFallback implements ProductServiceClient {
    @Override
    public ProductTmp detail(int pid) {
        ProductTmp productTmp = new ProductTmp();
        productTmp.setPid(-1);
        productTmp.setStock(0);
        return productTmp;
    }
}
