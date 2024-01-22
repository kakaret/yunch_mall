package com.zrrd.yunchmall.product.service;

import com.zrrd.yunchmall.product.entity.ProductAttribute;
import com.zrrd.yunchmall.product.entity.ProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
public interface IProductAttributeCategoryService extends IService<ProductAttributeCategory> {

    /**
     * 通过商品属性类型的id来查找对应的属性集合
     * @param id 属性类型的id
     * @return 属性集合
     */
    public List<ProductAttribute> selectProductAttributeById(long id);

}
