package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.FlashPromotion;
import com.zrrd.yunchmall.sale.mapper.FlashPromotionMapper;
import com.zrrd.yunchmall.sale.service.IFlashPromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
public class FlashPromotionServiceImpl extends ServiceImpl<FlashPromotionMapper, FlashPromotion> implements IFlashPromotionService {

    /**
     * 查询秒杀活动列表
     * @param pageNum 页号
     * @param pageSize 页大小
     * @param keyword 关键字
     * return pageInfo
     */
    @Override
    public IPage<FlashPromotion> list(int pageNum, int pageSize, String keyword) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(keyword)){
            queryWrapper.like("title",keyword);
        }
        IPage<FlashPromotion> pageInfo = super.page(new Page<>(pageNum,pageSize),queryWrapper);
        return pageInfo;
    }

    /**
     * 修改活动状态
     * @param id 编号
     * @param status 状态
     */
    @Override
    public void updateStatus(Long id, int status) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);
        FlashPromotion flashPromotion = super.getById(id);
        super.update(flashPromotion,updateWrapper);
    }
}
