package com.zrrd.yunchmall.product.service.impl;

import com.zrrd.yunchmall.product.entity.ProductCategoryAttributeRelation;
import com.zrrd.yunchmall.product.mapper.ProductCategoryAttributeRelationMapper;
import com.zrrd.yunchmall.product.service.IProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelation> implements IProductCategoryAttributeRelationService {

}
