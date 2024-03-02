package com.zrrd.yunchmall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.product.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
public interface IProductService extends IService<Product> {

    void freeStock(List<Map<String, Long>> params);

    Page page(String keyword, Integer publishStatus, Integer verifyStatus, String productSn, Integer productCategoryId, Integer brandId, int pageNum, int pageSize);
}
