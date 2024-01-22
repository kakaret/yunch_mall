package com.zrrd.yunchmall.sale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.HomeNewProduct;
import com.zrrd.yunchmall.sale.entity.HomeRecommendSubject;
import com.zrrd.yunchmall.sale.mapper.HomeRecommendSubjectMapper;
import com.zrrd.yunchmall.sale.service.IHomeRecommendSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 首页推荐专题表 服务实现类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Service
public class HomeRecommendSubjectServiceImpl extends ServiceImpl<HomeRecommendSubjectMapper, HomeRecommendSubject> implements IHomeRecommendSubjectService {

    /**
     * 分页查询
     * @param subjectName 专题名称
     * @param recommendStatus 推荐状态
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return 分页信息
     */
    @Override
    public Page<HomeRecommendSubject> pageInfo(String subjectName, Integer recommendStatus, Integer pageNum, Integer pageSize) {
        Page<HomeRecommendSubject> page = new Page<>(pageNum, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(subjectName)) {
            queryWrapper.like("product_name", recommendStatus);
        }
        if (recommendStatus != null) {
            queryWrapper.eq("recommend_status", recommendStatus);
        }
        return page(page, queryWrapper);
    }

    /**
     * 更新推荐状态
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
