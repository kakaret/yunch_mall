package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrrd.yunchmall.sale.entity.FlashPromotionSession;
import com.zrrd.yunchmall.sale.mapper.FlashPromotionSessionMapper;
import com.zrrd.yunchmall.sale.service.IFlashPromotionSessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
public class FlashPromotionSessionServiceImpl extends ServiceImpl<FlashPromotionSessionMapper, FlashPromotionSession> implements IFlashPromotionSessionService {

    /**
     * 修改秒杀时间段列表状态
     * @param id 编号
     * @param status 启用状态
     */
    @Override
    public void updateStatus(long id, int status) {
        FlashPromotionSession flashPromotionSession = super.getById(id);
        flashPromotionSession.setStatus(status);
        super.updateById(flashPromotionSession);
    }

    /**
     * 修改秒杀时间段列表
     * @param flashPromotionSession 秒杀时间段列表
     * @param id 编号
     */
    @Override
    public void updateList(FlashPromotionSession flashPromotionSession, long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);
        baseMapper.update(flashPromotionSession,updateWrapper);
    }
}
