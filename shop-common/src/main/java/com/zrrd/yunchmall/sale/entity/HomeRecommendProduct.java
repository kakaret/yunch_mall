package com.zrrd.yunchmall.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 人气推荐商品表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("sms_home_recommend_product")
@ApiModel(value = "HomeRecommendProduct对象", description = "人气推荐商品表")
public class HomeRecommendProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("推荐状态 0->不推荐，1->推荐")
    private Integer recommendStatus;

    @ApiModelProperty("排序")
    private Integer sort;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Integer recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "HomeRecommendProduct{" +
            "id = " + id +
            ", productId = " + productId +
            ", productName = " + productName +
            ", recommendStatus = " + recommendStatus +
            ", sort = " + sort +
        "}";
    }
}
