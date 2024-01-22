package com.zrrd.yunchmall.sale.service;

import com.zrrd.yunchmall.sale.entity.HomeRecommendProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface IHomeRecommendProductService extends IService<HomeRecommendProduct> {
    /**
     * 分页查询
     * @param productName 商品名称
     * @param recommendStatus 推荐状态
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return
     */
    Object pageInfo(String productName, Integer recommendStatus, Integer pageNum, Integer pageSize);

    /**
     * 修改推荐状态
     * @param ids id集合
     * @param recommendStatus 推荐状态
     * @return
     */
    Object recommendStatus(String ids, Integer recommendStatus);

    /**
     * 设置排序
     * @param id id
     * @param sort 排序
     * @return
     */
    Object updateSort(Long id, Integer sort);


}
