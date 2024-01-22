package com.zrrd.yunchmall.content.controller;

import com.zrrd.yunchmall.content.service.IPrefrenceAreaService;
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
 * 优选专区 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/prefrenceArea")
@Api(tags = "内容服务-会员喜好模块")
public class PrefrenceAreaController {
    @Autowired
    private IPrefrenceAreaService areaService;

    @ApiOperation("查询会员喜好列表")
    @GetMapping("/listAll")
    public ResponseResult list() {
        return new ResponseResult(200, "查询成功", areaService.list());
    }
}
