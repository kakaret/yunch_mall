package com.zrrd.yunchmall.pojo.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 限时购通知记录
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("sms_flash_promotion_log")
@ApiModel(value = "FlashPromotionLog对象", description = "限时购通知记录")
public class FlashPromotionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("会员id")
    private Integer memberId;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("会员手机号")
    private String memberPhone;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("会员订阅时间")
    private LocalDateTime subscribeTime;

    private LocalDateTime sendTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDateTime getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(LocalDateTime subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "FlashPromotionLog{" +
            "id = " + id +
            ", memberId = " + memberId +
            ", productId = " + productId +
            ", memberPhone = " + memberPhone +
            ", productName = " + productName +
            ", subscribeTime = " + subscribeTime +
            ", sendTime = " + sendTime +
        "}";
    }
}
