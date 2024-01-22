package com.zrrd.yunchmall.content.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.content.entity.SubjectProductRelation;
import com.zrrd.yunchmall.content.service.ISubjectProductRelationService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 专题商品关系表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/subjectProductRelation")
@Api(tags = "内容服务-专题商品关系模块")
public class SubjectProductRelationController {
    @Autowired
    ISubjectProductRelationService subjectProductRelationService;

    @ApiOperation("批量添加专题商品关系")
    @PostMapping("/create")
    public ResponseResult createSubjectRelation(@RequestBody List<SubjectProductRelation> list) {
        subjectProductRelationService.saveBatch(list);
        return new ResponseResult(200, "添加成功");
    }

    @ApiOperation("通过商品id查询关系")
    @GetMapping("/list/{id}")
    public ResponseResult list(@PathVariable("id") long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", id);
        return new ResponseResult(200, "查询成功", subjectProductRelationService.list(queryWrapper));
    }
}
