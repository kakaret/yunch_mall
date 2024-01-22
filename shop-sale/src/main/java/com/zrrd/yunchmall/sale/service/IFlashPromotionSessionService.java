package com.zrrd.yunchmall.sale.service;

import com.zrrd.yunchmall.sale.entity.FlashPromotionSession;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface IFlashPromotionSessionService extends IService<FlashPromotionSession> {

    /**
     * 修改秒杀时间段列表状态
     * @param id 编号
     * @param status 启用状态
     */
    void updateStatus(long id, int status);

    /**
     * 修改秒杀时间段列表
     * @param flashPromotionSession 秒杀时间段列表
     * @param id 编号
     */
    void updateList(FlashPromotionSession flashPromotionSession, long id);
}
