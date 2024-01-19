package com.zrrd.yunchmall.product.controller;

import com.zrrd.yunchmall.product.entity.ProductCategory;
import com.zrrd.yunchmall.product.service.IProductCategoryService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("查找商品类别及其子类别")
    @GetMapping("/list/withChildren")
    public ResponseResult listWithChildren() {
        List<ProductCategory> categoryList = service.listWithChildren();
        return new ResponseResult(200, "查询成功", categoryList);
    }


}
