package com.zrrd.yunchmall.user.controller;

import com.zrrd.yunchmall.user.service.IResourceCategoryService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 资源分类表 前端控制器
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/resourceCategory")
@Api(tags = "权限服务-资源类型模块")
public class ResourceCategoryController {
    @Autowired
    private IResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有资源信息")
    @GetMapping("/listAll")
    public ResponseResult listAll() {
        return new ResponseResult(200,"查询成功", resourceCategoryService.list());
    }
}
