package com.zrrd.yunchmall.sale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.CouponHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券使用、领取历史表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface ICouponHistoryService extends IService<CouponHistory> {

    /**
     * 查询优惠卷使用历史
     *
     * @param pageNum   页码
     * @param pageSize  每页显示条数
     * @param couponId  优惠券ID
     * @param useStatus 优惠券使用状态
     * @param orderSn   订单编号
     * @return 优惠卷使用历史
     */
    Page<CouponHistory> pageInfo(Integer pageNum, Integer pageSize, Long couponId, Integer useStatus, String orderSn);
}
