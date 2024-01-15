package com.zrrd.yunchmall.pojo.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 专题评论表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("cms_topic_comment")
@ApiModel(value = "TopicComment对象", description = "专题评论表")
public class TopicComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("会员昵称")
    private String memberNickName;

    @ApiModelProperty("话题id")
    private Long topicId;

    @ApiModelProperty("会员头像")
    private String memberIcon;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("显示状态：0->不推荐，1->推荐")
    private Integer showStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getMemberIcon() {
        return memberIcon;
    }

    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "TopicComment{" +
            "id = " + id +
            ", memberNickName = " + memberNickName +
            ", topicId = " + topicId +
            ", memberIcon = " + memberIcon +
            ", content = " + content +
            ", createTime = " + createTime +
            ", showStatus = " + showStatus +
        "}";
    }
}
