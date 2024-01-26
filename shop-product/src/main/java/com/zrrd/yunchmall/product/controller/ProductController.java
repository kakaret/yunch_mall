package com.zrrd.yunchmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

import java.util.List;
import java.util.Map;

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

    @ApiOperation("退货或关闭订单释放内存")
    @PostMapping("/stock/free")
    public ResponseResult freeStock(@RequestBody List<Map<String, Long>> params) {
        productService.freeStock(params);
        return new ResponseResult(200, "执行成功");
    }

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
        if (publishStatus != null)
            queryWrapper.like("publish_status", publishStatus);
        if (verifyStatus != null)
            queryWrapper.like("verify_status", verifyStatus);
        if (!StringUtils.isEmpty(productSn))
            queryWrapper.like("product_sn", productSn);
        if (productCategoryId != null)
            queryWrapper.like("product_category_id", productCategoryId);
        if (brandId != null)
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

    @ApiOperation("设为/取消推荐")
    @PostMapping("/update/recommendStatus")
    public ResponseResult updateRecommendStatus(@RequestParam @ApiParam("要修改的状态") int recommendStatus, @RequestParam @ApiParam("批量操作的Id") String ids) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("recommand_status", recommendStatus);
        updateWrapper.in("id", ids.split(","));
        productService.update(updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("设为/取消新品")
    @PostMapping("/update/newStatus")
    public ResponseResult updateNewStatus(@RequestParam @ApiParam("要修改的状态") int newStatus, @RequestParam @ApiParam("批量操作的Id") String ids) {
        UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("new_status", newStatus);
        updateWrapper.in("id", ids.split(","));
        productService.update(updateWrapper);
        return new ResponseResult(200, "修改成功");
    }


    @ApiOperation("新增商品信息")
    @PostMapping("/create")
    public ResponseResult create(@RequestBody Product product) {
        productService.save(product);
        return new ResponseResult(200, "新增商品成功");
    }

    @ApiOperation("查询单个要修改的商品信息")
    @GetMapping("/updateInfo/{id}")
    public ResponseResult updateInfo(@PathVariable("id") long id) {
        return new ResponseResult(200, "查询成功", productService.getById(id));
    }

    @ApiOperation("修改商品信息")
    @PostMapping("/update/{id}")
    public ResponseResult update(@PathVariable("id") long id, @RequestBody Product product) {
        productService.updateById(product);
        return new ResponseResult(200, "更新成功");
    }

    @ApiOperation("通过商品名称或商品货号查询商品信息")
    @GetMapping("/simpleList")
    public ResponseResult simpleList(String keyword) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("name", keyword).or().like("product_sn", keyword);
        }
        return new ResponseResult(200, "查询成功", productService.list(queryWrapper));
    }

    @RequestMapping("/testSleuth")
    public String testSleuth() {
        return "商品：测试链路追踪";
    }
}
