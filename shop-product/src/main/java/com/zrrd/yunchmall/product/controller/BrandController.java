package com.zrrd.yunchmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.product.entity.Brand;
import com.zrrd.yunchmall.product.service.IBrandService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "商品服务-品牌管理")
public class BrandController {
    @Autowired
    private IBrandService service;

    @ApiOperation("查询品牌列表")
    @GetMapping("/list")
    public ResponseResult list(@RequestParam(required = false) String keyword,
                               @RequestParam int pageNum,
                               @RequestParam int pageSize) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("name", keyword);
        }
        return new ResponseResult<>(200, "查询成功", service.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
