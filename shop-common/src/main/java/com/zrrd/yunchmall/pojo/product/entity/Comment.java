package com.zrrd.yunchmall.pojo.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 商品评价表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("pms_comment")
@ApiModel(value = "Comment对象", description = "商品评价表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("会员昵称")
    private String memberNickName;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("评价星数：0->5")
    private Integer star;

    @ApiModelProperty("评价的ip")
    private String memberIp;

    @ApiModelProperty("评价时间")
    private LocalDateTime createTime;

    @ApiModelProperty("是否显示")
    private Integer showStatus;

    @ApiModelProperty("购买时的商品属性")
    private String productAttribute;

    @ApiModelProperty("点赞数")
    private Integer collectCount;

    @ApiModelProperty("阅读次数")
    private Integer readCount;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("上传图片地址，以逗号隔开")
    private String pics;

    @ApiModelProperty("评论用户头像")
    private String memberIcon;

    @ApiModelProperty("回复数量")
    private Integer replayCount;

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

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getMemberIp() {
        return memberIp;
    }

    public void setMemberIp(String memberIp) {
        this.memberIp = memberIp;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(String productAttribute) {
        this.productAttribute = productAttribute;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getMemberIcon() {
        return memberIcon;
    }

    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }

    public Integer getReplayCount() {
        return replayCount;
    }

    public void setReplayCount(Integer replayCount) {
        this.replayCount = replayCount;
    }

    @Override
    public String toString() {
        return "Comment{" +
            "id = " + id +
            ", productId = " + productId +
            ", memberNickName = " + memberNickName +
            ", productName = " + productName +
            ", star = " + star +
            ", memberIp = " + memberIp +
            ", createTime = " + createTime +
            ", showStatus = " + showStatus +
            ", productAttribute = " + productAttribute +
            ", collectCount = " + collectCount +
            ", readCount = " + readCount +
            ", content = " + content +
            ", pics = " + pics +
            ", memberIcon = " + memberIcon +
            ", replayCount = " + replayCount +
        "}";
    }
}
