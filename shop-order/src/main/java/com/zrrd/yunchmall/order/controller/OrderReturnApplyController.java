package com.zrrd.yunchmall.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.order.entity.OrderReturnApply;
import com.zrrd.yunchmall.order.service.IOrderReturnApplyService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 * 订单退货申请 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/returnApply")
@Api(tags = "订单服务-退货申请模块")
public class OrderReturnApplyController {

    @Autowired
    private IOrderReturnApplyService orderReturnApplyService;

    @ApiOperation("查询单个退货申请")
    @GetMapping("/{id}")
    public ResponseResult getOne(@PathVariable("id") long id) {
        return new ResponseResult(200, "查询成功", orderReturnApplyService.getById(id));
    }

    @ApiOperation("查询退货申请列表")
    @GetMapping("/list")
    public ResponseResult list(@RequestParam int pageNum, @RequestParam int pageSize,
                               Integer id,
                               Integer status,
                               String createTime,
                               String handleMan,
                               String handleTime) {
        QueryWrapper<OrderReturnApply> queryWrapper = new QueryWrapper();
        if(id != null) {
            queryWrapper.like("id", id);
        }
        if(status != null) {
            queryWrapper.eq("status", status);
        }
        if(!StringUtils.isEmpty(createTime)) {
            queryWrapper.like("create_time", createTime);
        }
        if(!StringUtils.isEmpty(handleMan)) {
            queryWrapper.like("handle_man", handleMan);
        }
        if(!StringUtils.isEmpty(handleTime)) {
            queryWrapper.like("handle_time", handleTime);
        }
        return new ResponseResult(200, "查询成功", orderReturnApplyService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation("批量删除")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam String ids) {
        orderReturnApplyService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return new ResponseResult(200, "删除成功");
    }

    @ApiOperation("处理退货订单")
    @PostMapping("/update/status/{id}")
    public ResponseResult status(@PathVariable("id") long id, @RequestBody OrderReturnApply orderReturnApply) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("company_address_id", orderReturnApply.getCompanyAddressId());
        updateWrapper.set("handle_man", orderReturnApply.getHandleMan());
        updateWrapper.set("handle_note", orderReturnApply.getHandleNote());
        updateWrapper.set("receive_man", orderReturnApply.getReceiveMan());
        updateWrapper.set("receive_note", orderReturnApply.getReceiveNote());
        updateWrapper.set("return_amount", orderReturnApply.getReturnAmount());
        updateWrapper.set("status", orderReturnApply.getStatus());
        updateWrapper.set("handle_time", LocalDateTime.now());
        updateWrapper.eq("id", id);
        orderReturnApplyService.update(updateWrapper);
        return new ResponseResult(200, "处理成功");
    }
}
