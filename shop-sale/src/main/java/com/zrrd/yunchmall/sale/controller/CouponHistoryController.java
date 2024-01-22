package com.zrrd.yunchmall.sale.controller;

import com.zrrd.yunchmall.sale.service.ICouponHistoryService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 优惠券使用、领取历史表 前端控制器
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/couponHistory")
public class CouponHistoryController {
    @Autowired
    private ICouponHistoryService couponHistoryService;
    @GetMapping("/list")
    @ApiOperation("查询使用记录")
    public ResponseResult list(@RequestParam(defaultValue = "1") @ApiParam(value = "页码", required = true) Integer pageNum,
                               @RequestParam(defaultValue = "10") @ApiParam(value = "每页显示条数", required = true) Integer pageSize,
                               @RequestParam @ApiParam(value = "优惠券ID", required = true) Long couponId,
                               @RequestParam(required = false) @ApiParam(value = "优惠券使用状态", allowableValues = "0,1") Integer useStatus,
                               @RequestParam(required = false) @ApiParam(value = "订单编号") String orderSn){

        return new ResponseResult(200,"查询成功",couponHistoryService.pageInfo(pageNum,pageSize,couponId,useStatus,orderSn));
    }
}
