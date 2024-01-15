package com.zrrd.yunchmall.pojo.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 相册表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("pms_album")
@ApiModel(value = "Album对象", description = "相册表")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("相册名称")
    private String name;

    @ApiModelProperty("封面")
    private String coverPic;

    @ApiModelProperty("图片数量")
    private Integer picCount;

    @ApiModelProperty("序号")
    private Integer sort;

    @ApiModelProperty("文字描述")
    private String description;

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

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public Integer getPicCount() {
        return picCount;
    }

    public void setPicCount(Integer picCount) {
        this.picCount = picCount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Album{" +
            "id = " + id +
            ", name = " + name +
            ", coverPic = " + coverPic +
            ", picCount = " + picCount +
            ", sort = " + sort +
            ", description = " + description +
        "}";
    }
}
