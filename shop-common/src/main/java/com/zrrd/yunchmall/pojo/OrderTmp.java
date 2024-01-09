package com.zrrd.yunchmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_order")
public class OrderTmp {
    @TableId(type = IdType.AUTO)
    private Long oid;
    private Integer uid;
    private String username;
    private Integer pid;//商品id
    private String pname;//商品名称
    private Double pprice;//商品单价
    private Integer number;//购买数量
}