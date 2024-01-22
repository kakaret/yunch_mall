package com.zrrd.yunchmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.product.entity.ProductAttribute;
import com.zrrd.yunchmall.product.entity.ProductCategory;
import com.zrrd.yunchmall.product.entity.ProductCategoryAttributeRelation;
import com.zrrd.yunchmall.product.service.IProductAttributeService;
import com.zrrd.yunchmall.product.service.IProductCategoryAttributeRelationService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */

@RestController
@RequestMapping("/productAttribute")
@Api(tags = "商品服务-商品属性模块")
public class ProductAttributeController {

    @Autowired
    private IProductCategoryAttributeRelationService productCategoryAttributeRelationService;

    @Autowired
    private IProductAttributeService service;

    @ApiOperation("查询列表")
    @GetMapping("/list/{attributeCateId}")
    public ResponseResult list(@PathVariable("attributeCateId") int id, Integer pageNum, Integer pageSize, Integer type) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_attribute_category_id", id);
        if(type != null) {
            queryWrapper.eq("type", type);

        }
        if(pageNum == null && pageSize == null) return new ResponseResult(200, "查询成功", service.list(queryWrapper));
        return new ResponseResult(200, "查询成功", service.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation("查询单个商品分类对应的一级属性id和二级属性id")
    @GetMapping("/attrInfo/{id}")
    public ResponseResult attrInfo(@PathVariable("id") long id) {
        QueryWrapper<ProductCategoryAttributeRelation> wrapper = new QueryWrapper();
        wrapper.eq("product_category_id", id);
        List<ProductCategoryAttributeRelation> productAttrIdList = productCategoryAttributeRelationService.list(wrapper);
        List<ProductAttribute> productCategoryList = new ArrayList<>();
        if(productAttrIdList != null) {
            for(int i = 0; i < productAttrIdList.size(); i++) {
                productCategoryList.add(service.getById(productAttrIdList.get(i).getProductAttributeId()));
            }
        }
        return new ResponseResult(200, "查询成功", productCategoryList);
    }

    @ApiOperation("查询单一商品属性")
    @GetMapping("/{id}")
    public ResponseResult selectById(@PathVariable("id") long id) {
        return new ResponseResult(200, "查询成功", service.getById(id));
    }

    @ApiOperation("添加商品属性")
    @PostMapping("/create")
    public ResponseResult create(@RequestBody ProductAttribute productAttribute) {
        service.save(productAttribute);
        return new ResponseResult<>(200, "添加成功");
    }

    @ApiOperation("修改商品属性")
    @PostMapping("/update/{id}")
    public ResponseResult update(@PathVariable("id") long id, @RequestBody ProductAttribute productAttribute) {
        service.updateById(productAttribute);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("删除商品属性")
    @PostMapping("/delete")
    public ResponseResult delete(String ids) {
        service.removeBatchByIds(Arrays.asList(ids.split(",")));
        return new ResponseResult(200, "删除成功");
    }

}
