package com.zrrd.yunchmall.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优惠券使用、领取历史表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("sms_coupon_history")
@ApiModel(value = "CouponHistory对象", description = "优惠券使用、领取历史表")
public class CouponHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("优惠券id")
    private Long couponId;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("优惠码")
    private String couponCode;

    @ApiModelProperty("领取人昵称")
    private String memberNickname;

    @ApiModelProperty("获取类型：0->后台赠送；1->主动获取")
    private Integer getType;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("使用状态：0->未使用；1->已使用；2->已过期")
    private Integer useStatus;

    @ApiModelProperty("使用时间")
    private LocalDateTime useTime;

    @ApiModelProperty("订单编号")
    private Long orderId;

    @ApiModelProperty("订单号码")
    private String orderSn;

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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public Integer getGetType() {
        return getType;
    }

    public void setGetType(Integer getType) {
        this.getType = getType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public LocalDateTime getUseTime() {
        return useTime;
    }

    public void setUseTime(LocalDateTime useTime) {
        this.useTime = useTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    @Override
    public String toString() {
        return "CouponHistory{" +
            "id = " + id +
            ", couponId = " + couponId +
            ", memberId = " + memberId +
            ", couponCode = " + couponCode +
            ", memberNickname = " + memberNickname +
            ", getType = " + getType +
            ", createTime = " + createTime +
            ", useStatus = " + useStatus +
            ", useTime = " + useTime +
            ", orderId = " + orderId +
            ", orderSn = " + orderSn +
        "}";
    }
}
