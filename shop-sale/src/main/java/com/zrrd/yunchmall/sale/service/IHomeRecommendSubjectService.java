package com.zrrd.yunchmall.sale.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.HomeRecommendSubject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 首页推荐专题表 服务类
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
public interface IHomeRecommendSubjectService extends IService<HomeRecommendSubject> {
    /**
     * 分页查询
     * @param subjectName 专题名称
     * @param recommendStatus 推荐状态
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return 分页信息
     */
    Page<HomeRecommendSubject> pageInfo(String subjectName, Integer recommendStatus, Integer pageNum, Integer pageSize);

    /**
     * 更新推荐状态
     * @param ids id集合
     * @param recommendStatus 推荐状态
     * @return
     */
    Object recommendStatus(String ids, Integer recommendStatus);

    /**
     * 设置排序
     * @param id id
     * @param sort 排序
     * @return
     */

    Object updateSort(Long id, Integer sort);


}
