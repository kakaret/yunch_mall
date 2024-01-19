package com.zrrd.yunchmall.product.service;

import com.zrrd.yunchmall.product.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
public interface IProductCategoryService extends IService<ProductCategory> {

    /**
     * 查询并返回商品类别及其子类别
     * @return
     */
    List<ProductCategory> listWithChildren();
}
