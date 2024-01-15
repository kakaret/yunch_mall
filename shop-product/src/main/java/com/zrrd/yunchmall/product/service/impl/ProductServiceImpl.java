package com.zrrd.yunchmall.product.service.impl;

import com.zrrd.yunchmall.product.entity.Product;
import com.zrrd.yunchmall.product.mapper.ProductMapper;
import com.zrrd.yunchmall.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
