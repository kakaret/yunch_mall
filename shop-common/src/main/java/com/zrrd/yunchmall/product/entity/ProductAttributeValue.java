package com.zrrd.yunchmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 存储产品参数信息的表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("pms_product_attribute_value")
@ApiModel(value = "ProductAttributeValue对象", description = "存储产品参数信息的表")
public class ProductAttributeValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品属性id")
    private Long productAttributeId;

    @ApiModelProperty("手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductAttributeValue{" +
            "id = " + id +
            ", productId = " + productId +
            ", productAttributeId = " + productAttributeId +
            ", value = " + value +
        "}";
    }
}
