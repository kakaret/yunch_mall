package com.zrrd.yunchmall.sale.controller;

import com.zrrd.yunchmall.sale.entity.Coupon;
import com.zrrd.yunchmall.sale.service.ICouponService;
import com.zrrd.yunchmall.sale.service.ICouponService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 优惠券表 前端控制器
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private ICouponService couponService;

    @GetMapping("/list")
    @ApiOperation("查询优惠券列表")
    public ResponseResult list(@RequestParam(required = false) @ApiParam(value = "优惠券名称关键词") String name,
                               @RequestParam(required = false) @ApiParam(value = "赠券类型") Integer type,
                               @RequestParam(defaultValue = "1") @ApiParam(value = "页码", required = true) Integer pageNum,
                               @RequestParam(defaultValue = "10") @ApiParam(value = "每页显示条数", required = true) Integer pageSize) {
        return new ResponseResult(200, "查询成功", couponService.list(pageNum, pageSize, name, type));
    }


    @ApiOperation("添加优惠券")
    @PostMapping("/create")
    public ResponseResult creat(@RequestBody Coupon coupon) {
        couponService.save(coupon);
        return new ResponseResult(200, "添加成功", coupon);
    }

    @PostMapping("/update/{id}")
    @ApiOperation("修改优惠券")
    public ResponseResult update(@PathVariable @ApiParam(value = "优惠券ID", required = true) Long id,
                                 @RequestBody Coupon coupon) {
        couponService.updateById(coupon);
        return new ResponseResult(200, "修改成功");
    }

    @GetMapping("/{id}")
    @ApiOperation("查询优惠券信息")
    public ResponseResult info(@PathVariable @ApiParam(value = "优惠券ID", required = true) Long id){
        return new ResponseResult(200, "查询成功", couponService.getById(id));
    }

    @ApiOperation("删除优惠券")
    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        couponService.removeById(id);
        return new ResponseResult(200, "删除成功");
    }
}
