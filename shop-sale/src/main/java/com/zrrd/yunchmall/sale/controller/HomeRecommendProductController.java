package com.zrrd.yunchmall.sale.controller;
import com.zrrd.yunchmall.sale.entity.HomeRecommendProduct;
import com.zrrd.yunchmall.sale.service.IHomeNewProductService;
import com.zrrd.yunchmall.sale.service.IHomeRecommendProductService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 人气推荐商品表 前端控制器
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/home/recommendProduct")
@Api("热门商品推荐")
public class HomeRecommendProductController {
    @Autowired
    private IHomeRecommendProductService homeRecommendProductService;

    @GetMapping("/list")
    @ApiOperation("分页查询")
    public ResponseResult list(@RequestParam(required = false) @ApiParam(value = "商品名称关键词") String productName,
                               @RequestParam(required = false) @ApiParam(value = "推荐状态") Integer recommendStatus,
                               @RequestParam(defaultValue = "1") @ApiParam(value = "页码", required = true) Integer pageNum,
                               @RequestParam(defaultValue = "5") @ApiParam(value = "每页显示条数", required = true) Integer pageSize) {
        return new ResponseResult(200, "查询成功", homeRecommendProductService.pageInfo(productName, recommendStatus, pageNum, pageSize));
    }

    @PostMapping("/create")
    @ApiOperation("选择热门商品推荐")
    public ResponseResult create(@RequestBody List<HomeRecommendProduct> homeRecommendProductList) {
        return new ResponseResult(200, "添加成功", homeRecommendProductService.saveBatch(homeRecommendProductList));
    }

    @PostMapping("/update/recommendStatus")
    @ApiOperation("设置推荐/取消")
    public ResponseResult updateRecommendStatus(String ids, Integer recommendStatus) {
        return new ResponseResult(200, "设置成功", homeRecommendProductService.recommendStatus(ids, recommendStatus));

    }

    @PostMapping("/delete")
    @ApiOperation("删除新品推荐")
    public ResponseResult delete(String ids) {
        String[] idsArray = ids.split(",");
        return new ResponseResult(200, "删除成功", homeRecommendProductService.removeBatchByIds(Arrays.asList(idsArray)));

    }

    @PostMapping("/update/sort/{id}")
    @ApiOperation("设置推荐排序值")
    public ResponseResult updateSort(@PathVariable Long id, @RequestParam Integer sort) {
        return new ResponseResult(200, "设置成功", homeRecommendProductService.updateSort(id, sort));

    }
}
