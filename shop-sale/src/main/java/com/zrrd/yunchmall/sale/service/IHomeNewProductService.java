package com.zrrd.yunchmall.sale.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.HomeNewProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 新鲜好物表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface IHomeNewProductService extends IService<HomeNewProduct> {

    /**
     * 分页查询
     * @param productName 商品名称
     * @param recommendStatus 商品推荐状态
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return
     */
    Page<HomeNewProduct> pageInfo(String productName, Integer recommendStatus, Integer pageNum, Integer pageSize);

    /**
     * 根据id修改推荐状态
     * @param ids id集合
     * @param recommendStatus 商品推荐状态
     * @return
     */
    Object recommendStatus(String ids, Integer recommendStatus);

    /**
     * 根据id修改排序
     * @param id id
     * @param sort 排序
     * @return
     */
    Object updateSort(Long id, Integer sort);
}
