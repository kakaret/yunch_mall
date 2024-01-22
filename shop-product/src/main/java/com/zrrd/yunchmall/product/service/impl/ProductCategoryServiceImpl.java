package com.zrrd.yunchmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.product.entity.ProductCategory;
import com.zrrd.yunchmall.product.entity.ProductCategoryAttributeRelation;
import com.zrrd.yunchmall.product.mapper.ProductCategoryAttributeRelationMapper;
import com.zrrd.yunchmall.product.mapper.ProductCategoryMapper;
import com.zrrd.yunchmall.product.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {
    @Autowired
    private ProductCategoryAttributeRelationMapper relationMapper;

    //    后边可以对这个方法做一个缓存优化 将分类列表保存到Redis 提升这个方法的查询频率 减轻Mysql的压力
    @Override
    public List<ProductCategory> listWithChildren() {
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", 0).or().eq("level", 0);
//        查询一级分类
        List<ProductCategory> l1CateList = super.list(queryWrapper);
        l1CateList.forEach(cate -> {
            queryWrapper.clear();
            queryWrapper.eq("parent_id", cate.getId());
//            查询二级分类
            cate.setChildren(super.list(queryWrapper));
        });
        return l1CateList;
    }

    @Override
    @Transactional
    public boolean save(ProductCategory productCategory) {
        if (productCategory.getLevel() == null) productCategory.setLevel(0);
        super.save(productCategory);
        if (productCategory.getProductAttributeIdList() != null) {
            long id = productCategory.getId();
            List<Long> productAttributeIdList = productCategory.getProductAttributeIdList();
            productAttributeIdList.forEach(item -> {
                ProductCategoryAttributeRelation productCategoryAttributeRelation = new ProductCategoryAttributeRelation();
                productCategoryAttributeRelation.setProductCategoryId(id);
                productCategoryAttributeRelation.setProductAttributeId(item);
                relationMapper.insert(productCategoryAttributeRelation);
            });
        }
        return true;
    }
}
