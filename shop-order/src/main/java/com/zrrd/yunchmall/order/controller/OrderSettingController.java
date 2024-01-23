package com.zrrd.yunchmall.order.controller;

import com.zrrd.yunchmall.order.entity.OrderSetting;
import com.zrrd.yunchmall.order.service.IOrderSettingService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 订单设置表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/orderSetting")
@Api(tags = "订单服务-订单设置模块")
public class OrderSettingController {
    @Autowired
    private IOrderSettingService orderSettingService;

    @ApiOperation("查询订单设置")
    @GetMapping("/{id}")
    public ResponseResult list(@PathVariable("id") long id) {
        return new ResponseResult(200, "查询成功", orderSettingService.getById(id));
    }

    @ApiOperation("更改订单设置")
    @PostMapping("/{id}")
    public ResponseResult update(@PathVariable("id") long id, @RequestBody OrderSetting orderSetting) {
        orderSettingService.updateById(orderSetting);
        return new ResponseResult(200, "更改成功");
    }
}
