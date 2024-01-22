package com.zrrd.yunchmall.content.controller;

import com.zrrd.yunchmall.content.service.ISubjectService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 专题表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/subject")
@Api(tags = "内容服务-专题模块")
public class SubjectController {

    @Autowired
    private ISubjectService service;

    @ApiOperation("查询专题列表")
    @GetMapping("/listAll")
    public ResponseResult list() {
        return new ResponseResult(200, "查询成功", service.list());
    }
}
