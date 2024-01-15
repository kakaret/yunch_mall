package com.zrrd.yunchmall.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优惠券和产品的关系表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("sms_coupon_product_relation")
@ApiModel(value = "CouponProductRelation对象", description = "优惠券和产品的关系表")
public class CouponProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("优惠券id")
    private Long couponId;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品编码")
    private String productSn;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    @Override
    public String toString() {
        return "CouponProductRelation{" +
            "id = " + id +
            ", couponId = " + couponId +
            ", productId = " + productId +
            ", productName = " + productName +
            ", productSn = " + productSn +
        "}";
    }
}
