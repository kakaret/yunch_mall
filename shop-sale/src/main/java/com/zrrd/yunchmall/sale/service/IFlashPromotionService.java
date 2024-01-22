package com.zrrd.yunchmall.sale.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrrd.yunchmall.sale.entity.FlashPromotion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface IFlashPromotionService extends IService<FlashPromotion> {

    /**
     * 查询秒杀活动列表
     * @param pageNum 页号
     * @param pageSize 页大小
     * @param keyword 关键字
     * return pageInfo
     */
    IPage<FlashPromotion> list(int pageNum, int pageSize, String keyword);

    /**
     * 修改活动状态
     * @param id 编号
     * @param status 状态
     */
    void updateStatus(Long id, int status);
}
