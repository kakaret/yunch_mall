package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.Coupon;
import com.zrrd.yunchmall.sale.mapper.CouponMapper;
import com.zrrd.yunchmall.sale.service.ICouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {


    /**
     * 查询优惠券列表
     * @param pageNum 页号
     * @param pageSize 页大小
     * @param name 优惠券名称
     * @param type 优惠券类型
     * @return 分页信息
     */
    @Override
    public Page<Coupon> list(Integer pageNum, Integer pageSize, String name, Integer type) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(name))
            queryWrapper.like("name", name);
        if (type != null)
            queryWrapper.eq("type", type);
        return this.page(new Page<>(pageNum, pageSize), queryWrapper);
    }
}
