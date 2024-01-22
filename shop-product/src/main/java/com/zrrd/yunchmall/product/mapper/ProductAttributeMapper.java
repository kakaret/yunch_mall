package com.zrrd.yunchmall.product.mapper;

import com.zrrd.yunchmall.product.entity.ProductAttribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Repository
public interface ProductAttributeMapper extends BaseMapper<ProductAttribute> {
    @Select("select * from pms_product_attribute where product_attribute_category_id = #{id}")
    List<ProductAttribute> listProductAttribute(@Param("id") long id);
}
