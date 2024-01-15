package com.zrrd.yunchmall.pojo.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 公司收发货地址表
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@TableName("oms_company_address")
@ApiModel(value = "CompanyAddress对象", description = "公司收发货地址表")
public class CompanyAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("地址名称")
    private String addressName;

    @ApiModelProperty("默认发货地址：0->否；1->是")
    private Integer sendStatus;

    @ApiModelProperty("是否默认收货地址：0->否；1->是")
    private Integer receiveStatus;

    @ApiModelProperty("收发货人姓名")
    private String name;

    @ApiModelProperty("收货人电话")
    private String phone;

    @ApiModelProperty("省/直辖市")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区")
    private String region;

    @ApiModelProperty("详细地址")
    private String detailAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "CompanyAddress{" +
            "id = " + id +
            ", addressName = " + addressName +
            ", sendStatus = " + sendStatus +
            ", receiveStatus = " + receiveStatus +
            ", name = " + name +
            ", phone = " + phone +
            ", province = " + province +
            ", city = " + city +
            ", region = " + region +
            ", detailAddress = " + detailAddress +
        "}";
    }
}
