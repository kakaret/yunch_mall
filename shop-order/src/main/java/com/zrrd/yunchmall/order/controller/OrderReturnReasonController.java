package com.zrrd.yunchmall.order.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.order.entity.OrderReturnApply;
import com.zrrd.yunchmall.order.entity.OrderReturnReason;
import com.zrrd.yunchmall.order.service.IOrderReturnReasonService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

/**
 * <p>
 * 退货原因表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/returnReason")
@Api(tags = "订单模块-退货原因模块")
public class OrderReturnReasonController {
    @Autowired
    private IOrderReturnReasonService orderReturnReasonService;

    @ApiOperation("查询列表")
    @GetMapping("/list")
    public ResponseResult list(int pageNum, int pageSize) {
        return new ResponseResult(200, "查询成功", orderReturnReasonService.page(new Page<>(pageNum, pageSize)));
    }


    @ApiOperation("新建退货原因")
    @PostMapping("/create")
    public ResponseResult create(@RequestBody OrderReturnReason orderReturnReason) {
        orderReturnReasonService.save(orderReturnReason);
        return new ResponseResult(200, "新建成功");
    }

    @ApiOperation("批量删除")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam String ids) {
        orderReturnReasonService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return new ResponseResult(200, "删除成功");
    }

    @ApiOperation("是否可用")
    @PostMapping("/status")
    public ResponseResult status(long status, String ids) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", ids);
        updateWrapper.set("status", status);
        orderReturnReasonService.update(updateWrapper);
        return new ResponseResult(200, "处理成功");
    }


    @ApiOperation("查询单个退货原因")
    @GetMapping("/{id}")
    public ResponseResult getOne(@PathVariable("id") long id) {
        return new ResponseResult(200, "查询成功", orderReturnReasonService.getById(id));
    }

    @ApiOperation("修改单个退货原因")
    @PostMapping("/update/{id}")
    public ResponseResult update(@PathVariable("id") long id, @RequestBody OrderReturnReason orderReturnReason){
        orderReturnReasonService.updateById(orderReturnReason);
        return new ResponseResult(200, "修改成功");
    }
}
