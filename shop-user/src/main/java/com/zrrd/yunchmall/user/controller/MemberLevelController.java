package com.zrrd.yunchmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.user.entity.MemberLevel;
import com.zrrd.yunchmall.user.service.IMemberLevelService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
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
 * 会员等级表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/memberLevel")
@Api("用户服务-会员等级模块")
public class MemberLevelController {

    @Autowired
    private IMemberLevelService service;

    @ApiOperation("查询会员列表")
    @GetMapping("/list")
    public ResponseResult list(@ApiParam @RequestParam(required = false) Integer defaultStatus) {
        QueryWrapper<MemberLevel> queryWrapper = new QueryWrapper<>();
        if(defaultStatus != null) {
            queryWrapper.eq("default_status", defaultStatus);
        }
        return new ResponseResult<>(200, "查询成功", service.list(queryWrapper));
    }
}
