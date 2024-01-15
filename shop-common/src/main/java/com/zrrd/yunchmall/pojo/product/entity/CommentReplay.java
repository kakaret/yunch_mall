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
 * 产品评价回复表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("pms_comment_replay")
@ApiModel(value = "CommentReplay对象", description = "产品评价回复表")
public class CommentReplay implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("评论id")
    private Long commentId;

    @ApiModelProperty("回复评论的会员昵称")
    private String memberNickName;

    @ApiModelProperty("回复评论的会员头像")
    private String memberIcon;

    @ApiModelProperty("回复的内容")
    private String content;

    @ApiModelProperty("时间")
    private LocalDateTime createTime;

    @ApiModelProperty("评论人员类型；0->会员；1->管理员")
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CommentReplay{" +
            "id = " + id +
            ", commentId = " + commentId +
            ", memberNickName = " + memberNickName +
            ", memberIcon = " + memberIcon +
            ", content = " + content +
            ", createTime = " + createTime +
            ", type = " + type +
        "}";
    }
}
