package com.zrrd.yunchmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.product.entity.ProductAttribute;
import com.zrrd.yunchmall.product.entity.ProductCategory;
import com.zrrd.yunchmall.product.entity.ProductCategoryAttributeRelation;
import com.zrrd.yunchmall.product.service.IProductCategoryAttributeRelationService;
import com.zrrd.yunchmall.product.service.IProductCategoryService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/productCategory")
@Api(tags = "商品服务-商品类别模块")
public class ProductCategoryController {
    @Autowired
    private IProductCategoryService service;

    @Autowired
    private IProductCategoryAttributeRelationService relationService;

    @ApiOperation("查询列表")
    @GetMapping("/list/{cateId}")
    public ResponseResult list(@PathVariable("cateId") int cateId, int pageNum, int pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", cateId);
        return new ResponseResult(200, "查询成功", service.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation("查找全部商品类别及其子类别")
    @GetMapping("/list/withChildren")
    public ResponseResult listWithChildren() {
        List<ProductCategory> categoryList = service.listWithChildren();
        return new ResponseResult(200, "查询成功", categoryList);
    }

    @ApiOperation("开启/关闭导航栏")
    @PostMapping("/update/navStatus")
    public ResponseResult updateNavStatus(int ids, int navStatus) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", ids);
        updateWrapper.set("nav_status", navStatus);
        service.update(updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("开启/关闭显示")
    @PostMapping("/update/showStatus")
    public ResponseResult updateShowStatus(int ids, int showStatus) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", ids);
        updateWrapper.set("nav_status", showStatus);
        service.update(updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("添加商品分类")
    @PostMapping("/create")
    public ResponseResult create(@RequestBody ProductCategory productCategory) {
        service.save(productCategory);
        return new ResponseResult<>(200, "添加成功");
    }

    @ApiOperation("查询单个商品的分类")
    @GetMapping("/{id}")
    public ResponseResult updateCate(@PathVariable("id") long id) {
        return new ResponseResult(200, "查询成功", service.getById(id));
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/delete/{id}")
    @Transactional
    public ResponseResult delete(@PathVariable("id") long id) {
        service.removeById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_category_id", id);
        relationService.remove(queryWrapper);
        return new ResponseResult(200, "删除成功");
    }

}
