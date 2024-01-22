package com.zrrd.yunchmall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.product.entity.Brand;
import com.zrrd.yunchmall.product.service.IBrandService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
            queryWrapper.like("name", keyword).or().eq("first_letter", keyword);
        }
        return new ResponseResult<>(200, "查询成功", service.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation("开/关品牌制造商")
    @PostMapping("/update/factoryStatus")
    public ResponseResult updateFactoryStatus(String ids, int factoryStatus) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", ids);
        updateWrapper.set("factory_status", factoryStatus);
        service.update(updateWrapper);
        return new ResponseResult<>(200, "修改成功");
    }

    @ApiOperation("是否显示品牌")
    @PostMapping("/update/showStatus")
    public ResponseResult updateShowStatus(String ids, int showStatus) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("show_status", showStatus);
        updateWrapper.in("id", ids.split(","));
        service.update(updateWrapper);
        return new ResponseResult<>(200, "修改成功");
    }

    @ApiOperation("查找单个品牌")
    @GetMapping("/{id}")
    public ResponseResult selectById(@PathVariable("id") long id) {
        return new ResponseResult(200, "查找成功", service.getById(id));
    }

    @ApiOperation("添加品牌")
    @PostMapping("/create")
    public ResponseResult create(@RequestBody Brand brand) {
        service.save(brand);
        return new ResponseResult<>(200, "添加成功");
    }

    @ApiOperation("编辑品牌")
    @PostMapping("/update/{id}")
    public ResponseResult updateById(@PathVariable("id") long id, @RequestBody Brand brand) {
        service.updateById(brand);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("删除品牌")
    @GetMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") long id) {
        service.removeById(id);
        return new ResponseResult(200, "删除成功");
    }

}
