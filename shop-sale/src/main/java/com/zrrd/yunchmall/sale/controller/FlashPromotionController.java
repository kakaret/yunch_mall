package com.zrrd.yunchmall.sale.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.sale.entity.FlashPromotion;
import com.zrrd.yunchmall.sale.service.IFlashPromotionLogService;
import com.zrrd.yunchmall.sale.service.IFlashPromotionService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 限时购表 前端控制器
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/flash")
@Api("营销服务-秒杀活动列表")
public class FlashPromotionController {
    @Autowired
    private IFlashPromotionService flashService;

    @GetMapping("/list")
    @ApiOperation("查询秒杀活动列表")
    public ResponseResult list(@RequestParam int pageNum, @RequestParam int pageSize,String keyword) {
        return new ResponseResult(200,"查询成功",flashService.list(pageNum,pageSize,keyword));
    }

    @PostMapping("/update/status/{id}")
    @ApiOperation("修改活动状态")
    public ResponseResult updateStatus(@PathVariable("id") Long id,@RequestParam int status) {
        flashService.updateStatus(id,status);
        return new ResponseResult(200,"修改成功");
    }

    @ApiOperation("添加活动")
    @PostMapping("/create")
    public ResponseResult creat(@RequestBody FlashPromotion flashPromotion) {
        flashService.save(flashPromotion);
        return new ResponseResult(200,"添加成功",flashPromotion);
    }

    @ApiOperation("修改活动")
    @PostMapping("/update/{id}")
    public ResponseResult update(@PathVariable long id ,@RequestBody FlashPromotion flashPromotion) {
        flashService.updateById(flashPromotion);
        return new ResponseResult(200,"修改成功",flashPromotion);
    }

    @ApiOperation("删除活动")
    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        flashService.removeById(id);
        return new ResponseResult(200,"删除成功");
    }

}