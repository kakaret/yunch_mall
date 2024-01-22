package com.zrrd.yunchmall.content.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.content.entity.PrefrenceAreaProductRelation;
import com.zrrd.yunchmall.content.service.IPrefrenceAreaProductRelationService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优选专区和产品关系表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/prefrenceAreaProductRelation")
@Api(tags = "内容服务-用户喜好和商品关系模块")
public class PrefrenceAreaProductRelationController {

    @Autowired
    private IPrefrenceAreaProductRelationService service;

    @ApiOperation("添加喜好商品关系")
    @PostMapping("/create")
    public ResponseResult createPrefrenceAreaRelation(@RequestBody List<PrefrenceAreaProductRelation> list) {
        service.saveBatch(list);
        return new ResponseResult(200, "添加成功");
    }

    @ApiOperation("通过商品id查询关系")
    @GetMapping("/list/{id}")
    public ResponseResult list(@PathVariable("id") long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", id);
        return new ResponseResult(200, "查询成功", service.list(queryWrapper));
    }

    @ApiOperation("通过商品id删除关系")
    @GetMapping("/delete/{id}")
    public ResponseResult deleteSPR(@PathVariable("id") long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", id);
        service.remove(queryWrapper);
        return new ResponseResult(200, "删除成功");
    }
}
