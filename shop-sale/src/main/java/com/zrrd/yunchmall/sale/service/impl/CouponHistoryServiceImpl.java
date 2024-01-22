package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.CouponHistory;
import com.zrrd.yunchmall.sale.mapper.CouponHistoryMapper;
import com.zrrd.yunchmall.sale.service.ICouponHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryMapper, CouponHistory> implements ICouponHistoryService {

    /**
     * 查询优惠卷使用历史
     * @param pageNum 页码
     * @param pageSize 每页显示条数
     * @param couponId 优惠券ID
     * @param useStatus 优惠券使用状态
     * @param orderSn 订单编号
     * @return 优惠卷使用历史
     */
    @Override
    public Page<CouponHistory> pageInfo(Integer pageNum, Integer pageSize, Long couponId, Integer useStatus, String orderSn) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("coupon_id", couponId);
        if (orderSn != null) {
            queryWrapper.like("order_sn", orderSn);
        }
        if (useStatus != null) {
            queryWrapper.like("use_status", useStatus);
        }
        return this.page(new Page<>(pageNum, pageSize), queryWrapper);
    }
}
