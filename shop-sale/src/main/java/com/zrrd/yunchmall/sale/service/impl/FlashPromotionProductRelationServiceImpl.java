package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.FlashPromotionProductRelation;
import com.zrrd.yunchmall.sale.mapper.FlashPromotionProductRelationMapper;
import com.zrrd.yunchmall.sale.service.IFlashPromotionProductRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品限时购与商品关系表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
@SuppressWarnings("all")
public class FlashPromotionProductRelationServiceImpl extends ServiceImpl<FlashPromotionProductRelationMapper, FlashPromotionProductRelation> implements IFlashPromotionProductRelationService {

    @Autowired
    private FlashPromotionProductRelationMapper flashPromotionProductRelationMapper;

    /**
     * 根据秒杀活动id和秒杀时间段id查询商品列表
     * @param pageNum 页号
     * @param pageSize 页大小
     * @param flashPromotionId 秒杀活动id
     * @param flashPromotionSessionId 秒杀时间段id
     * @return
     */
    @Override
    public IPage<FlashPromotionProductRelation> selectProductByFlashPromotionIdAndFlashPromotionSessionId(int pageNum, int pageSize, long flashPromotionId, long flashPromotionSessionId) {
        QueryWrapper<FlashPromotionProductRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("flash_promotion_id", flashPromotionId).eq("flash_promotion_session_id", flashPromotionSessionId);
        IPage page = page(new Page<>(pageNum, pageSize), wrapper);
        return flashPromotionProductRelationMapper.selectProductByFlashPromotionIdAndFlashPromotionSessionId(page, flashPromotionId,  flashPromotionSessionId);
    }

    /**
     * 根据秒杀活动id和秒杀时间段id更新商品信息
     * @param id 秒杀活动id
     * @param relation 秒杀商品信息
     */

    @Override
    public void updateById(Long id, FlashPromotionProductRelation relation) {
        UpdateWrapper<FlashPromotionProductRelation> wrapper = new UpdateWrapper<>();
        wrapper.eq("flash_promotion_id", id);
        flashPromotionProductRelationMapper.update(relation, wrapper);
    }
}
