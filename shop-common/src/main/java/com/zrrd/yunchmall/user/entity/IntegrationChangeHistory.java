package com.zrrd.yunchmall.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 积分变化历史记录表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("ums_integration_change_history")
@ApiModel(value = "IntegrationChangeHistory对象", description = "积分变化历史记录表")
public class IntegrationChangeHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("改变类型：0->增加；1->减少")
    private Integer changeType;

    @ApiModelProperty("积分改变数量")
    private Integer changeCount;

    @ApiModelProperty("操作人员")
    private String operateMan;

    @ApiModelProperty("操作备注")
    private String operateNote;

    @ApiModelProperty("积分来源：0->购物；1->管理员修改")
    private Integer sourceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Integer getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(Integer changeCount) {
        this.changeCount = changeCount;
    }

    public String getOperateMan() {
        return operateMan;
    }

    public void setOperateMan(String operateMan) {
        this.operateMan = operateMan;
    }

    public String getOperateNote() {
        return operateNote;
    }

    public void setOperateNote(String operateNote) {
        this.operateNote = operateNote;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "IntegrationChangeHistory{" +
            "id = " + id +
            ", memberId = " + memberId +
            ", createTime = " + createTime +
            ", changeType = " + changeType +
            ", changeCount = " + changeCount +
            ", operateMan = " + operateMan +
            ", operateNote = " + operateNote +
            ", sourceType = " + sourceType +
        "}";
    }
}
