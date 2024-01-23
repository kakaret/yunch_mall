package com.zrrd.yunchmall.sale.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrrd.yunchmall.sale.entity.FlashPromotion;
import com.zrrd.yunchmall.sale.entity.FlashPromotionProductRelation;
import com.zrrd.yunchmall.sale.entity.FlashPromotionSession;
import com.zrrd.yunchmall.sale.service.IFlashPromotionProductRelationService;
import com.zrrd.yunchmall.sale.service.IFlashPromotionService;
import com.zrrd.yunchmall.sale.service.IFlashPromotionSessionService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 限时购场次表 前端控制器
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Api(tags = "营销-秒杀时间段列表")
@RestController
@RequestMapping("/flashSession")
public class FlashPromotionSessionController {
    @Autowired
    private IFlashPromotionSessionService flashPromotionSessionService;

    @Autowired
    private IFlashPromotionProductRelationService productRelationService;

    @Autowired
    private IFlashPromotionService flashPromotionService;
    @ApiOperation("查询")
    @GetMapping("/list")
    public ResponseResult list() {
        List<FlashPromotionSession> flashPromotionSessions = flashPromotionSessionService.list();
        return new ResponseResult(200,"查询成功",flashPromotionSessions);
    }

    @ApiOperation("修改秒杀时间段列表状态")
    @PostMapping("/update/status/{id}")
    public ResponseResult updateStatus(@PathVariable long id,@RequestParam int status) {
        flashPromotionSessionService.updateStatus(id,status);
        return new ResponseResult(200,"修改成功");
    }

    @ApiOperation("添加秒杀时间段列表")
    @PostMapping("/create")
    public ResponseResult creat(@RequestBody FlashPromotionSession flashPromotionSession) {
        flashPromotionSessionService.save(flashPromotionSession);
        return new ResponseResult(200,"添加成功",flashPromotionSession);
    }

    @ApiOperation("修改秒杀时间段列表")
    @PostMapping("/update/{id}")
    public ResponseResult creat(@RequestBody FlashPromotionSession flashPromotionSession,@PathVariable long id) {
        flashPromotionSessionService.updateList(flashPromotionSession,id);
        return new ResponseResult(200,"添加成功",flashPromotionSession);
    }

    @ApiOperation("删除秒杀时间列表")
    @PostMapping("/delete/{id}")
    public ResponseResult deleteList(@PathVariable long id){
        flashPromotionSessionService.removeById(id);
        return new ResponseResult(200,"删除成功");
    }


    @ApiOperation("查询单个活动")
    @GetMapping("/selectList")
    public ResponseResult selectList(long flashPromotionId) {
        return new ResponseResult(200, "查询成功", flashPromotionSessionService.selectFlashList(flashPromotionId));
    }
}
