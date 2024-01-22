package com.zrrd.yunchmall.product.mapper;

import com.zrrd.yunchmall.product.entity.ProductAttribute;
import com.zrrd.yunchmall.product.entity.ProductAttributeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Repository
public interface ProductAttributeCategoryMapper extends BaseMapper<ProductAttributeCategory> {

}
