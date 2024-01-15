package com.zrrd.yunchmall.pojo.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优惠券和产品分类关系表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("sms_coupon_product_category_relation")
@ApiModel(value = "CouponProductCategoryRelation对象", description = "优惠券和产品分类关系表")
public class CouponProductCategoryRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("优惠券id")
    private Long couponId;

    @ApiModelProperty("商品类别id")
    private Long productCategoryId;

    @ApiModelProperty("产品分类名称")
    private String productCategoryName;

    @ApiModelProperty("父分类名称")
    private String parentCategoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    @Override
    public String toString() {
        return "CouponProductCategoryRelation{" +
            "id = " + id +
            ", couponId = " + couponId +
            ", productCategoryId = " + productCategoryId +
            ", productCategoryName = " + productCategoryName +
            ", parentCategoryName = " + parentCategoryName +
        "}";
    }
}
