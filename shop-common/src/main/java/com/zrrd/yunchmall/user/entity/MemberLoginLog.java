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
 * 会员登录记录
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("ums_member_login_log")
@ApiModel(value = "MemberLoginLog对象", description = "会员登录记录")
public class MemberLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("所在城市")
    private String city;

    @ApiModelProperty("登录类型：0->PC；1->android;2->ios;3->小程序")
    private Integer loginType;

    @ApiModelProperty("省份")
    private String province;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "MemberLoginLog{" +
            "id = " + id +
            ", memberId = " + memberId +
            ", createTime = " + createTime +
            ", ip = " + ip +
            ", city = " + city +
            ", loginType = " + loginType +
            ", province = " + province +
        "}";
    }
}
