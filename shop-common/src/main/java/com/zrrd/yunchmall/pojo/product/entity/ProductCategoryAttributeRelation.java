package com.zrrd.yunchmall.pojo.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("pms_product_category_attribute_relation")
@ApiModel(value = "ProductCategoryAttributeRelation对象", description = "产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）")
public class ProductCategoryAttributeRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品类别id")
    private Long productCategoryId;

    @ApiModelProperty("商品属性id")
    private Long productAttributeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    @Override
    public String toString() {
        return "ProductCategoryAttributeRelation{" +
            "id = " + id +
            ", productCategoryId = " + productCategoryId +
            ", productAttributeId = " + productAttributeId +
        "}";
    }
}
