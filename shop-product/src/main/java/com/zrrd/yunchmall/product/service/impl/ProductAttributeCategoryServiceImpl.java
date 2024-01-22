package com.zrrd.yunchmall.product.service.impl;

import com.zrrd.yunchmall.product.entity.ProductAttribute;
import com.zrrd.yunchmall.product.entity.ProductAttributeCategory;
import com.zrrd.yunchmall.product.mapper.ProductAttributeCategoryMapper;
import com.zrrd.yunchmall.product.mapper.ProductAttributeMapper;
import com.zrrd.yunchmall.product.service.IProductAttributeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements IProductAttributeCategoryService {

    @Autowired
    private ProductAttributeMapper attributeMapper;

    @Override
    public List<ProductAttribute> selectProductAttributeById(long id) {
        return attributeMapper.listProductAttribute(id);
    }
}
