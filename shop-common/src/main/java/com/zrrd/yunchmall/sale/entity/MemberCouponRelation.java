package com.zrrd.yunchmall.sale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("sms_member_coupon_relation")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberCouponRelation {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long memberId;

    private Long couponId;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime userTime;
}
