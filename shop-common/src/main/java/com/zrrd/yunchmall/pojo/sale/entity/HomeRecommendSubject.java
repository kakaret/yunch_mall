package com.zrrd.yunchmall.pojo.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 首页推荐专题表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("sms_home_recommend_subject")
@ApiModel(value = "HomeRecommendSubject对象", description = "首页推荐专题表")
public class HomeRecommendSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("主题id")
    private Long subjectId;

    @ApiModelProperty("主题名称")
    private String subjectName;

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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
        return "HomeRecommendSubject{" +
            "id = " + id +
            ", subjectId = " + subjectId +
            ", subjectName = " + subjectName +
            ", recommendStatus = " + recommendStatus +
            ", sort = " + sort +
        "}";
    }
}
