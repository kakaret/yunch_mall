package com.zrrd.yunchmall.pojo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 会员收货地址表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("ums_member_receive_address")
@ApiModel(value = "MemberReceiveAddress对象", description = "会员收货地址表")
public class MemberReceiveAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("收货人名称")
    private String name;

    @ApiModelProperty("收货人电话")
    private String phoneNumber;

    @ApiModelProperty("是否为默认")
    private Integer defaultStatus;

    @ApiModelProperty("邮政编码")
    private String postCode;

    @ApiModelProperty("省份/直辖市")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("区")
    private String region;

    @ApiModelProperty("详细地址(街道)")
    private String detailAddress;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        return "MemberReceiveAddress{" +
            "id = " + id +
            ", memberId = " + memberId +
            ", name = " + name +
            ", phoneNumber = " + phoneNumber +
            ", defaultStatus = " + defaultStatus +
            ", postCode = " + postCode +
            ", province = " + province +
            ", city = " + city +
            ", region = " + region +
            ", detailAddress = " + detailAddress +
        "}";
    }
}
