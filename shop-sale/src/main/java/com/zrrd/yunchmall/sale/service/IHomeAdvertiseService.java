package com.zrrd.yunchmall.sale.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.HomeAdvertise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface IHomeAdvertiseService extends IService<HomeAdvertise> {

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
    Page<HomeAdvertise> pageInfo(String name, Integer type, String endTime, Integer pageNum, Integer pageSize);

    /**
     * 通过id修改商品广告的状态
     *
     * @param id     编号
     * @param status 上下线状态：0->下线；1->上线
     * @return
     */
    Object updateStatus(Long id, Integer status);

    /**
     * 修改广告内容
     * @param id  编号
     * @param advertise 广告轮播表
     * @return
     */
    Object updateSort(Long id, HomeAdvertise advertise);
}
