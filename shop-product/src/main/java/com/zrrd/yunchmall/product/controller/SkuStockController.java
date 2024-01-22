package com.zrrd.yunchmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.product.entity.SkuStock;
import com.zrrd.yunchmall.product.service.ISkuStockService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * sku的库存 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/sku")
@Api(tags = "商品服务-sku库存模块")
public class SkuStockController {

    @Autowired
    private ISkuStockService skuStockService;

    @ApiOperation("查询Sku信息")
    @GetMapping("/{productId}")
    public ResponseResult pList(@PathVariable("productId") long productId, String keyword) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", productId);
        if(!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("sku_code", keyword);
        }
        return new ResponseResult(200, "查询成功", skuStockService.list(queryWrapper));
    }

    @ApiOperation("更新Sku信息")
    @PostMapping("/update/{id}")
    @Transactional
    public ResponseResult update(@PathVariable("id") long id, @RequestBody List<SkuStock> skuStockList) {
        skuStockList.forEach(skuStock -> {
            skuStockService.updateById(skuStock);
        });
        return new ResponseResult(200, "更新成功");
    }
}
