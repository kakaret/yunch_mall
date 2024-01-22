package com.zrrd.yunchmall.sale.controller;

import com.zrrd.yunchmall.sale.entity.HomeAdvertise;
import com.zrrd.yunchmall.sale.service.IHomeAdvertiseService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/home/advertise")
@Api(tags = "广告列表管理")
public class HomeAdvertiseController {
    @Autowired
    private IHomeAdvertiseService homeAdvertiseService;

    @GetMapping("/list")
    @ApiOperation("广告列表")
    public ResponseResult list(@RequestParam(required = false) @ApiParam(value = "广告名称关键词") String name,
                               @RequestParam(required = false) @ApiParam(value = "广告投放方式：0-PC首页 1-APP首页") Integer type,
                               @RequestParam(required = false) @ApiParam(value = "到期日期") String endTime,
                               @RequestParam(defaultValue = "1") @ApiParam(value = "页码", required = true) Integer pageNum,
                               @RequestParam(defaultValue = "10") @ApiParam(value = "每页显示条数", required = true) Integer pageSize) {
        return new ResponseResult(200, "查询成功", homeAdvertiseService.pageInfo(name, type, endTime, pageNum, pageSize));

    }

    @PostMapping("/create")
    @ApiOperation("新增广告")
    public ResponseResult create(@RequestBody HomeAdvertise advertise) {
        return new ResponseResult(200, "添加成功", homeAdvertiseService.save(advertise));

    }

    @PostMapping("/update/status/{id}")
    @ApiOperation("修改广告状态")
    public ResponseResult updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        return new ResponseResult(200, "设置成功", homeAdvertiseService.updateStatus(id, status));

    }

    @PostMapping("/delete")
    @ApiOperation("删除广告位")
    public ResponseResult delete(@RequestParam String ids) {
        String[] idsArray = ids.split(",");
        return new ResponseResult(200, "删除成功", homeAdvertiseService.removeBatchByIds(Arrays.asList(idsArray)));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询广告详情")
    public ResponseResult info(@PathVariable Long id) {
        return new ResponseResult(200,"查询成功",homeAdvertiseService.getById(id));
    }
    @PostMapping("/update/{id}")
    @ApiOperation("修改广告内容")
    public ResponseResult update(@PathVariable Long id, @RequestBody HomeAdvertise advertise){
        return new ResponseResult(200,"设置成功",homeAdvertiseService.updateSort(id, advertise));

    }
}
