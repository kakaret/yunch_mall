package com.zrrd.yunchmall.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 限时购表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("sms_flash_promotion")
@ApiModel(value = "FlashPromotion对象", description = "限时购表")
public class FlashPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("秒杀活动标题")
    private String title;

    @ApiModelProperty("开始日期")
    private LocalDate startDate;

    @ApiModelProperty("结束日期")
    private LocalDate endDate;

    @ApiModelProperty("上下线状态")
    private Integer status;

    @ApiModelProperty("秒杀时间段名称")
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
        return "FlashPromotion{" +
            "id = " + id +
            ", title = " + title +
            ", startDate = " + startDate +
            ", endDate = " + endDate +
            ", status = " + status +
            ", createTime = " + createTime +
        "}";
    }
}
