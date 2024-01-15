package com.zrrd.yunchmall.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 退货原因表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("oms_order_return_reason")
@ApiModel(value = "OrderReturnReason对象", description = "退货原因表")
public class OrderReturnReason implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("退货类型")
    private String name;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty("添加时间")
    private LocalDateTime createTime;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderReturnReason{" +
            "id = " + id +
            ", name = " + name +
            ", sort = " + sort +
            ", status = " + status +
            ", createTime = " + createTime +
        "}";
    }
}
