package com.zrrd.yunchmall.sale.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrrd.yunchmall.sale.entity.FlashPromotionProductRelation;
import com.zrrd.yunchmall.sale.service.IFlashPromotionProductRelationService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 前端控制器
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@Api(tags = "秒杀活动列表-设置商品")
@RestController
@RequestMapping("/flashProductRelation")
public class FlashPromotionProductRelationController {
    @Autowired
    private IFlashPromotionProductRelationService flashPromotionProductRelationService;
    @ApiOperation("查询商品列表")
    @GetMapping("/list")
    public ResponseResult list(@RequestParam(defaultValue = "1") @ApiParam(value = "页码", required = true) Integer pageNum,
                               @RequestParam(defaultValue = "5") @ApiParam(value = "每页显示条数", required = true) Integer pageSize,
                               @RequestParam @ApiParam(value = "秒杀活动ID", required = true) Long flashPromotionId,
                               @RequestParam @ApiParam(value = "秒杀时间段ID", required = true) Long flashPromotionSessionId){
        IPage<FlashPromotionProductRelation> flashPromotionProductRelationIPage = flashPromotionProductRelationService.selectProductByFlashPromotionIdAndFlashPromotionSessionId
                (pageNum, pageSize, flashPromotionId, flashPromotionSessionId);
        return new ResponseResult(200,"查询成功",flashPromotionProductRelationIPage);
    }

    @PostMapping("/create")
    @ApiOperation("添加秒杀商品")
    public ResponseResult create(@RequestBody List<FlashPromotionProductRelation> relationList){
        flashPromotionProductRelationService.saveBatch(relationList);
        return new ResponseResult(200,"添加成功");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除秒杀商品")
    public ResponseResult delete(@PathVariable @ApiParam(value = "限时购-商品关系ID", required = true) Long id){
        flashPromotionProductRelationService.removeById(id);
        return new ResponseResult(200,"删除成功");
    }

    @PostMapping("/update/{id}")
    @ApiOperation("修改秒杀商品信息")
    public ResponseResult update(@PathVariable @ApiParam(value = "限时购-商品关系ID", required = true) Long id,
                                 @RequestBody FlashPromotionProductRelation relation){
        flashPromotionProductRelationService.updateById(id,relation);
        return new ResponseResult(200,"修改成功");
    }

}
