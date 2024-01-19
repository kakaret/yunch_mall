package com.zrrd.yunchmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.product.entity.Product;
import com.zrrd.yunchmall.product.service.IProductService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/product")
@Api(tags = "商品服务-商品管理模块")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ApiOperation("查询商品列表")
    @GetMapping("/list")
    public ResponseResult list(@ApiParam("商品名称关键字") String keyword,
                               @RequestParam int pageNum,
                               @RequestParam int pageSize,
                               @ApiParam("上架状态") Integer publishStatus,
                               @ApiParam("审核状态") Integer verifyStatus,
                               @ApiParam("商品编码") String productSn,
                               @ApiParam("商品类别ID") Integer productCategoryId,
                               @ApiParam("品牌ID") Integer brandId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword))
            queryWrapper.like("name", keyword);
        if(publishStatus != null)
            queryWrapper.like("publish_status", publishStatus);
        if(verifyStatus != null)
            queryWrapper.like("verify_status", verifyStatus);
        if(!StringUtils.isEmpty(productSn))
            queryWrapper.like("product_sn", productSn);
        if(productCategoryId != null)
            queryWrapper.like("product_category_id", productCategoryId);
        if(brandId != null)
            queryWrapper.like("brand_id", brandId);
        return new ResponseResult<>(200, "查询成功", productService.page(new Page<>(pageNum, pageSize), queryWrapper));

    }


    @ApiOperation("添加/删除商品（回收站）")
    @PostMapping("/update/deleteStatus")
    public ResponseResult updateDeleteStatus(@RequestParam @ApiParam("要修改的状态") int deleteStatus, @RequestParam @ApiParam("批量操作的Id") String ids) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
//        update pms_product set delete_status = #{deleteStatus} where id in (#{ids})
        updateWrapper.set("delete_status", deleteStatus);
        updateWrapper.in("id", ids.split(","));
        productService.update(updateWrapper);
        return new ResponseResult<>(200, "修改成功");
    }


    @ApiOperation("上架/下架商品")
    @PostMapping("/update/publishStatus")
    public ResponseResult updatePublishStatus(@RequestParam @ApiParam("要修改的状态") int publishStatus, @RequestParam @ApiParam("批量操作的Id") String ids) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("publish_status", publishStatus);
        updateWrapper.in("id", ids.split(","));
        productService.update(updateWrapper);
        return new ResponseResult<>(200, "修改成功");
    }


    @RequestMapping("/testSleuth")
    public String testSleuth() {
        return "商品：测试链路追踪";
    }
}
