package com.zrrd.yunchmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.user.service.IMemberLevelService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/memberLevel")
@Api(tags = "用户服务-会员等级模块")
public class MemberLevelController {
    @Autowired
    private IMemberLevelService memberLevelService;
    @ApiOperation("查询列表")
    @GetMapping("/list")
    public ResponseResult list(@RequestParam(required = false) Integer defaultStatus) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (defaultStatus != null) {
            queryWrapper.eq("default_status", defaultStatus);
        }
        return new ResponseResult(200,"查询成功",memberLevelService.list(queryWrapper));
    }
}
