package com.zrrd.yunchmall.pojo.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 首页推荐品牌表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("sms_home_brand")
@ApiModel(value = "HomeBrand对象", description = "首页推荐品牌表")
public class HomeBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("品牌名称")
    private String brandName;

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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
        return "HomeBrand{" +
            "id = " + id +
            ", brandId = " + brandId +
            ", brandName = " + brandName +
            ", recommendStatus = " + recommendStatus +
            ", sort = " + sort +
        "}";
    }
}
