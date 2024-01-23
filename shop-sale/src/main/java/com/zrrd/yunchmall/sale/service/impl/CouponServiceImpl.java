package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.Coupon;
import com.zrrd.yunchmall.sale.entity.CouponProductCategoryRelation;
import com.zrrd.yunchmall.sale.entity.CouponProductRelation;
import com.zrrd.yunchmall.sale.mapper.CouponMapper;
import com.zrrd.yunchmall.sale.service.ICouponProductCategoryRelationService;
import com.zrrd.yunchmall.sale.service.ICouponProductRelationService;
import com.zrrd.yunchmall.sale.service.ICouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

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

    @Autowired
    private ICouponProductCategoryRelationService CategoryRelationService;

    @Autowired
    private ICouponProductRelationService couponProductRelationService;

    @Override
    public boolean save(Coupon coupon) {
        super.save(coupon);

        List<CouponProductCategoryRelation> couponProductCategoryRelationList = coupon.getProductCategoryRelationList();
        couponProductCategoryRelationList.forEach(item -> {
            item.setCouponId(coupon.getId());
        });
        CategoryRelationService.saveBatch(couponProductCategoryRelationList);

        List<CouponProductRelation> couponProductRelationList = coupon.getProductRelationList();
        couponProductRelationList.forEach(item -> {
            item.setCouponId(coupon.getId());
        });
        couponProductRelationService.saveBatch(couponProductRelationList);

        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("coupon_id", id);

        CategoryRelationService.remove(queryWrapper);
        couponProductRelationService.remove(queryWrapper);

        return true;
    }

    @Override
    public Coupon getById(Serializable id) {
        Coupon coupon = super.getById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("coupon_id", coupon.getId());
        coupon.setProductCategoryRelationList(CategoryRelationService.list(queryWrapper));
        coupon.setProductRelationList(couponProductRelationService.list(queryWrapper));
        return coupon;
    }

    @Override
    public boolean updateById(Coupon coupon) {
        super.updateById(coupon);
        CategoryRelationService.updateBatchById(coupon.getProductCategoryRelationList());
        couponProductRelationService.updateBatchById(coupon.getProductRelationList());
        return true;
    }

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
