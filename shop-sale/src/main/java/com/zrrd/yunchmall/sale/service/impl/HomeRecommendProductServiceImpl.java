package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.HomeNewProduct;
import com.zrrd.yunchmall.sale.entity.HomeRecommendProduct;
import com.zrrd.yunchmall.sale.mapper.HomeRecommendProductMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.sale.service.IHomeRecommendProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
public class HomeRecommendProductServiceImpl extends ServiceImpl<HomeRecommendProductMapper, HomeRecommendProduct> implements IHomeRecommendProductService {

    /**
     * 分页查询
     * @param productName 商品名称
     * @param recommendStatus 推荐状态
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return
     */
    @Override
    public Page<HomeRecommendProduct> pageInfo(String productName, Integer recommendStatus, Integer pageNum, Integer pageSize) {
        Page<HomeRecommendProduct> page = new Page<>(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(productName)) {
            queryWrapper.like("product_name", productName);
        }
        if (recommendStatus != null) {
            queryWrapper.eq("recommend_status", recommendStatus);
        }
        return page(page, queryWrapper);
    }

    /**
     * 修改推荐状态
     * @param ids id集合
     * @param recommendStatus 推荐状态
     * @return
     */
    @Override
    public Object recommendStatus(String ids, Integer recommendStatus) {
        String[] isList = ids.split(",");
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.in("id", isList);
        updateWrapper.set("recommend_status", recommendStatus);
        return super.update(updateWrapper);
    }

    /**
     * 设置排序
     * @param id id
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
