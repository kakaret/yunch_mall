package com.zrrd.yunchmall.sale.service.impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.HomeAdvertise;
import com.zrrd.yunchmall.sale.mapper.HomeAdvertiseMapper;
import com.zrrd.yunchmall.sale.service.IHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
public class HomeAdvertiseServiceImpl extends ServiceImpl<HomeAdvertiseMapper, HomeAdvertise> implements IHomeAdvertiseService {

    /**
     * 分页查询广告列表
     *
     * @param name     广告名称
     * @param type     轮播位置：0->PC首页轮播；1->app首页轮播
     * @param endTime  到期时间
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return 首页广告轮播图
     */
    @Override
    public Page<HomeAdvertise> pageInfo(String name, Integer type, String endTime, Integer pageNum, Integer pageSize) {
        Page<HomeAdvertise> page = new Page<>(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (type != null) {
            queryWrapper.like("type", type);
        }
        if (endTime != null) {
            queryWrapper.eq("end_time", endTime);
        }
        return page(page, queryWrapper);
    }

    /**
     * 通过id修改商品广告的状态
     *
     * @param id     编号
     * @param status 上下线状态：0->下线；1->上线
     * @return
     */
    @Override
    public Object updateStatus(Long id, Integer status) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", id);
        updateWrapper.set("status", status);
        return super.update(updateWrapper);
    }

    /**
     * 修改广告内容
     * @param id  编号
     * @param advertise 广告轮播表
     * @return
     */
    @Override
    public Object updateSort(Long id, HomeAdvertise advertise) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", id);
        return update(advertise, updateWrapper);
    }
}
