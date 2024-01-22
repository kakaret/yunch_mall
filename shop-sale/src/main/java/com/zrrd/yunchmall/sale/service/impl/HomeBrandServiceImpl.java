package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.FlashPromotion;
import com.zrrd.yunchmall.sale.entity.HomeBrand;
import com.zrrd.yunchmall.sale.mapper.HomeBrandMapper;
import com.zrrd.yunchmall.sale.service.IHomeBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hamcrest.core.Every;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 首页推荐品牌表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
public class HomeBrandServiceImpl extends ServiceImpl<HomeBrandMapper, HomeBrand> implements IHomeBrandService {

    /**
     * 品牌推荐分页查询
     * @param brandName 品牌名称
     * @param recommendStatus 推荐状态 0->不推荐，1->推荐
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public Page<HomeBrand> pageInfo(String brandName, Integer recommendStatus, Integer pageNum, Integer pageSize) {
        Page<HomeBrand> page = new Page<>(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(brandName)) {
            queryWrapper.like("brand_name", brandName);
        }
        if (recommendStatus != null) {
            queryWrapper.eq("recommend_status", recommendStatus);
        }
        return page(page, queryWrapper);
    }

    /**
     * 更新推荐状态
     * @param ids 编号集合
     * @param recommendStatus 推荐状态 0->不推荐，1->推荐
     * @return
     */
    @Override
    public Object updateRecommendStatus(String ids, Integer recommendStatus) {
        String[] idList = ids.split(",");
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.in("id", idList);
        updateWrapper.set("recommend_status", recommendStatus);
        return super.update(updateWrapper);
    }

    /**
     *设置排序
     * @param id 品牌推荐编号
     * @param sort 排序
     * @return
     */
    @Override
    public Object updateSort(Long id, Integer sort) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", id);
        updateWrapper.set("sort", sort);
        return super.update(updateWrapper);
    }
}
