package com.zrrd.yunchmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.product.entity.ProductAttributeCategory;
import com.zrrd.yunchmall.product.service.IProductAttributeCategoryService;
import com.zrrd.yunchmall.product.service.IProductAttributeService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/productAttribute/category")
@Api(tags = "商品服务-商品属性类别模块")
public class ProductAttributeCategoryController {

    @Autowired
    private IProductAttributeCategoryService attributeCategoryService;

    @ApiOperation("查询列表")
    @GetMapping("/list")
    public ResponseResult list(int pageNum, int pageSize, String keyword) {
        QueryWrapper queryWrapper = null;
        if(!StringUtils.isEmpty(keyword)) {
            queryWrapper = new QueryWrapper();
            queryWrapper.like("name", keyword);
        }

        return new ResponseResult(200, "查询成功", attributeCategoryService.page(new Page<>(pageNum, pageSize), queryWrapper));

    }


    @ApiOperation("查询全部属性")
    @GetMapping("/list/withAttr")
    public ResponseResult listWithAttr() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<ProductAttributeCategory> attrCateList = attributeCategoryService.list();
        attrCateList.forEach(attrCate -> {
//            通过类别属性Id查找对应的属性列表
            attrCate.setProductAttributeList(attributeCategoryService.selectProductAttributeById(attrCate.getId()));
        });
        return new ResponseResult(200, "查询成功", attrCateList);
    }

    @ApiOperation("修改属性")
    @PostMapping("/update/{id}")
    public ResponseResult update(@PathVariable("id") long id, @RequestParam String name) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", id);
        updateWrapper.set("name", name);
        attributeCategoryService.update(updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("添加属性")
    @PostMapping("/create")
    public ResponseResult create(@RequestParam String name) {
        ProductAttributeCategory productAttributeCategory = new ProductAttributeCategory();
        productAttributeCategory.setName(name);
        attributeCategoryService.save(productAttributeCategory);
        return new ResponseResult(200, "添加成功");
    }

    @ApiOperation("删除属性")
    @GetMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") long id) {
        attributeCategoryService.removeById(id);
        return new ResponseResult(200, "删除成功");
    }
}
