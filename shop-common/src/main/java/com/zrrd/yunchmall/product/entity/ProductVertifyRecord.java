package com.zrrd.yunchmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 商品审核记录
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("pms_product_vertify_record")
@ApiModel(value = "ProductVertifyRecord对象", description = "商品审核记录")
public class ProductVertifyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("审核人")
    private String vertifyMan;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("反馈详情")
    private String detail;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getVertifyMan() {
        return vertifyMan;
    }

    public void setVertifyMan(String vertifyMan) {
        this.vertifyMan = vertifyMan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ProductVertifyRecord{" +
            "id = " + id +
            ", productId = " + productId +
            ", createTime = " + createTime +
            ", vertifyMan = " + vertifyMan +
            ", status = " + status +
            ", detail = " + detail +
        "}";
    }
}
