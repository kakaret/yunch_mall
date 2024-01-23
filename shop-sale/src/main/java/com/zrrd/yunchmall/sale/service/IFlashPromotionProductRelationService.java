package com.zrrd.yunchmall.sale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.FlashPromotionProductRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品限时购与商品关系表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface IFlashPromotionProductRelationService extends IService<FlashPromotionProductRelation> {


    /**
     * 根据秒杀活动id和秒杀时间段id查询商品列表
     * @param pageNum 页号
     * @param pageSize 页大小
     * @param flashPromotionId 秒杀活动id
     * @param flashPromotionSessionId 秒杀时间段id
     * @return
     */
    IPage<FlashPromotionProductRelation> selectProductByFlashPromotionIdAndFlashPromotionSessionId(int pageNum, int pageSize, long flashPromotionId, long flashPromotionSessionId);

    void updateById(Long id, FlashPromotionProductRelation relation);
}
