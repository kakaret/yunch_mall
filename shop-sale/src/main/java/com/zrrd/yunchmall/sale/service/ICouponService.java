package com.zrrd.yunchmall.sale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface ICouponService extends IService<Coupon> {

    /**
     * 修改优惠卷
     * @param pageNum 页号
     * @param pageSize 页大小
     * @param name 优惠卷名称
     * @param type 优惠卷类型
     * @return
     */
    Page<Coupon> list(Integer pageNum, Integer pageSize, String name, Integer type);
}
