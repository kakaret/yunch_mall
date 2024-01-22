package com.zrrd.yunchmall.sale.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.HomeBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 首页推荐品牌表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface IHomeBrandService extends IService<HomeBrand> {

    /**
     * 品牌推荐分页查询
     * @param brandName 品牌名称
     * @param recommendStatus 推荐状态 0->不推荐，1->推荐
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
    Page<HomeBrand> pageInfo(String brandName, Integer recommendStatus, Integer pageNum, Integer pageSize);

    /**
     * 更新推荐状态
     * @param ids 编号集合
     * @param recommendStatus 推荐状态 0->不推荐，1->推荐
     * @return
     */
    Object updateRecommendStatus(String ids, Integer recommendStatus);

    /**
     *设置排序
     * @param id 品牌推荐编号
     * @param sort 排序
     * @return
     */
    Object updateSort(Long id, Integer sort);
}
