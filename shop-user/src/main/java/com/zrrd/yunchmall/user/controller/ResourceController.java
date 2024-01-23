package com.zrrd.yunchmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.user.entity.Resource;
import com.zrrd.yunchmall.user.entity.Role;
import com.zrrd.yunchmall.user.service.IResourceService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 后台资源表 前端控制器
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/resource")
@Api(tags = "权限服务-资源模块")
public class ResourceController {
    @Autowired
    private IResourceService resourceService;

    @ApiOperation("查询所有资源信息")
    @GetMapping("/listAll")
    public ResponseResult listAll() {
        return new ResponseResult(200,"查询成功", resourceService.list());
    }

    @ApiOperation("查询资源列表")
    @GetMapping("/list")
    public ResponseResult list(@ApiParam(required = true,defaultValue = "1") @RequestParam int pageSize,
                               @ApiParam(required = true,defaultValue = "10") @RequestParam int pageNum,
                               @ApiParam @RequestParam(required = false) String nameKeyword,
                               @ApiParam @RequestParam(required = false) String urlKeyword,
                               @ApiParam @RequestParam(required = false) String categoryId) {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(nameKeyword)) {//当关键词不为空
            queryWrapper.like("name", nameKeyword);
        }
        if (!StringUtils.isEmpty(urlKeyword)) {
            queryWrapper.like("url", urlKeyword);
        }
        if (!StringUtils.isEmpty(categoryId)) {
            queryWrapper.like("category_id",categoryId);
        }
        IPage<Resource> pageInfo = resourceService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return new ResponseResult(200, "查询成功", pageInfo);
    }

    @ApiOperation("添加一个资源")
    @PostMapping("/create")
    public ResponseResult create(@RequestBody Resource resource) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",resource.getName());

        if (resourceService.count(queryWrapper) > 0) {
            return new ResponseResult(205,"添加失败，角色已存在！");
        }
        resourceService.save(resource);
        return new ResponseResult(200,"添加成功");
    }

    @ApiOperation("修改资源信息")
    @PostMapping("/update/{id}")
    public ResponseResult update(@RequestBody Resource resource, @PathVariable("id") long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);// where 字句
        resourceService.update(resource,updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("删除资源")
    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") long id) {
        resourceService.removeById(id);
        return new ResponseResult(200,"删除成功");
    }
}
