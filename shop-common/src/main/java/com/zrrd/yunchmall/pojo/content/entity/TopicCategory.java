package com.zrrd.yunchmall.pojo.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 话题分类表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("cms_topic_category")
@ApiModel(value = "TopicCategory对象", description = "话题分类表")
public class TopicCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("类别名称")
    private String name;

    @ApiModelProperty("分类图标")
    private String icon;

    @ApiModelProperty("专题数量")
    private Integer subjectCount;

    @ApiModelProperty("显示状态：0->不推荐，1->推荐")
    private Integer showStatus;

    @ApiModelProperty("排序")
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSubjectCount() {
        return subjectCount;
    }

    public void setSubjectCount(Integer subjectCount) {
        this.subjectCount = subjectCount;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "TopicCategory{" +
            "id = " + id +
            ", name = " + name +
            ", icon = " + icon +
            ", subjectCount = " + subjectCount +
            ", showStatus = " + showStatus +
            ", sort = " + sort +
        "}";
    }
}
