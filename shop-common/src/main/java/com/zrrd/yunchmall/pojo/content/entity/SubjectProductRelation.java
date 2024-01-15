package com.zrrd.yunchmall.pojo.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 专题商品关系表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("cms_subject_product_relation")
@ApiModel(value = "SubjectProductRelation对象", description = "专题商品关系表")
public class SubjectProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("专题id")
    private Long subjectId;

    @ApiModelProperty("商品id")
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "SubjectProductRelation{" +
            "id = " + id +
            ", subjectId = " + subjectId +
            ", productId = " + productId +
        "}";
    }
}
