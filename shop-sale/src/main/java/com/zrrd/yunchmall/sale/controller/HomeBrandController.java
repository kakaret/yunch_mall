package com.zrrd.yunchmall.sale.controller;

import com.zrrd.yunchmall.sale.entity.HomeBrand;
import com.zrrd.yunchmall.sale.service.IHomeBrandService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 前端控制器
 * </p>
 *
 * @author LiuLuSheng
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/home/brand")
@Api(tags = "营销模块-品牌推荐")
public class HomeBrandController {
    @Autowired
    private IHomeBrandService homeBrandService;

    @GetMapping("/list")
    @ApiOperation("分页查询")
    public ResponseResult list(@RequestParam(required = false) @ApiParam(value = "品牌名称关键词") String brandName,
                               @RequestParam(required = false) @ApiParam(value = "推荐状态") Integer recommendStatus,
                               @RequestParam(defaultValue = "1") @ApiParam(value = "页码", required = true) Integer pageNum,
                               @RequestParam(defaultValue = "5") @ApiParam(value = "每页显示条数", required = true) Integer pageSize) {
        return new ResponseResult(200,"查询成功",homeBrandService.pageInfo(brandName, recommendStatus, pageNum, pageSize));
    }

    @PostMapping("/create")
    @ApiOperation("选择推荐品牌")
    public ResponseResult create(@RequestBody List<HomeBrand> homeBrandList){
        return new ResponseResult(200,"添加成功",homeBrandService.saveBatch(homeBrandList));
    }

    @PostMapping("/update/recommendStatus")
    @ApiOperation("设置推荐/取消")
    public ResponseResult updateRecommendStatus(String ids, Integer recommendStatus){
        return new ResponseResult(200,"设置成功",homeBrandService.updateRecommendStatus(ids, recommendStatus));
    }

    @PostMapping("/delete")
    @ApiOperation("删除品牌推荐")
    public ResponseResult delete(String ids){
        String[] idsArray = ids.split(",");
        return new ResponseResult(200,"删除成功",homeBrandService.removeBatchByIds(Arrays.asList(idsArray)));
    }

    @PostMapping("/update/sort/{id}")
    @ApiOperation("设置设置推荐排序值")
    public ResponseResult updateSort(@PathVariable Long id, @RequestParam Integer sort){
        return new ResponseResult(200,"设置成功",homeBrandService.updateSort(id, sort));
    }
}
