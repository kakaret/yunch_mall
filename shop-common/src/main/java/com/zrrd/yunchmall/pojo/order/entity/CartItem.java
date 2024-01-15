package com.zrrd.yunchmall.pojo.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("oms_cart_item")
@ApiModel(value = "CartItem对象", description = "购物车表")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("购物车ID，主键，自动递增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品ID")
    private Long productId;

    @ApiModelProperty("商品SKU库存ID")
    private Long productSkuId;

    @ApiModelProperty("会员ID")
    private Long memberId;

    @ApiModelProperty("购买数量")
    private Integer quantity;

    @ApiModelProperty("添加到购物车的价格")
    private BigDecimal price;

    @ApiModelProperty("商品主图")
    private String productPic;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品副标题（卖点）")
    private String productSubTitle;

    @ApiModelProperty("商品sku条码")
    private String productSkuCode;

    @ApiModelProperty("会员昵称")
    private String memberNickname;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("修改时间")
    private LocalDateTime modifyDate;

    @ApiModelProperty("是否删除")
    private Integer deleteStatus;

    @ApiModelProperty("商品分类")
    private Long productCategoryId;

    @ApiModelProperty("品牌")
    private String productBrand;

    @ApiModelProperty("商品编码")
    private String productSn;

    @ApiModelProperty("商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    private String productAttr;

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

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubTitle() {
        return productSubTitle;
    }

    public void setProductSubTitle(String productSubTitle) {
        this.productSubTitle = productSubTitle;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    @Override
    public String toString() {
        return "CartItem{" +
            "id = " + id +
            ", productId = " + productId +
            ", productSkuId = " + productSkuId +
            ", memberId = " + memberId +
            ", quantity = " + quantity +
            ", price = " + price +
            ", productPic = " + productPic +
            ", productName = " + productName +
            ", productSubTitle = " + productSubTitle +
            ", productSkuCode = " + productSkuCode +
            ", memberNickname = " + memberNickname +
            ", createDate = " + createDate +
            ", modifyDate = " + modifyDate +
            ", deleteStatus = " + deleteStatus +
            ", productCategoryId = " + productCategoryId +
            ", productBrand = " + productBrand +
            ", productSn = " + productSn +
            ", productAttr = " + productAttr +
        "}";
    }
}
