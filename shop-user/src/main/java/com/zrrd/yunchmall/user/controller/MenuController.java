package com.zrrd.yunchmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.user.entity.Menu;
import com.zrrd.yunchmall.user.entity.Resource;
import com.zrrd.yunchmall.user.entity.Role;
import com.zrrd.yunchmall.user.service.IMenuService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 后台菜单表 前端控制器
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "权限服务-菜单模块")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation("查询菜单树")
    @GetMapping("/treeList")
    public ResponseResult treeList() {
        List<Menu> treeList = menuService.getTreeList();

        return new ResponseResult(200,"查询成功",treeList);
    }

    @ApiOperation("查询菜单列表")
    @GetMapping("/list/{id}")
    public ResponseResult list(@ApiParam(required = true,defaultValue = "1") @RequestParam int pageSize,
                               @ApiParam(required = true,defaultValue = "10") @RequestParam int pageNum,
                               @PathVariable("id") long id) {
        //List<Menu> menus = menuService.getTreeList();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        IPage<Menu> pageInfo = menuService.page(new Page<>(pageNum,pageSize), queryWrapper);
        return new ResponseResult(200, "查询成功", pageInfo);
    }

    @ApiOperation("添加一个菜单")
    @PostMapping("/create")
    public ResponseResult create(@RequestBody Menu menu) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",menu.getName());

        if (menuService.count(queryWrapper) > 0) {
            return new ResponseResult(205,"添加失败，菜单已存在！");
        }
        if (menu.getLevel() == null) menu.setLevel(0);
        menu.setCreateTime(LocalDateTime.now());
        menuService.save(menu);
        return new ResponseResult(200,"添加成功");
    }

    @ApiOperation("查看菜单信息")
    @GetMapping("/{id}")
    public ResponseResult update(@PathVariable("id") long id) {
        Menu menu = menuService.getById(id);
        return new ResponseResult(200,"查询成功",menu);
    }

    @ApiOperation("修改菜单信息")
    @PostMapping("/update/{id}")
    public ResponseResult update(@RequestBody Menu menu, @PathVariable("id") long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);// where 字句
        menuService.update(menu,updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("删除菜单")
    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") long id) {
        menuService.removeById(id);
        return new ResponseResult(200,"删除成功");
    }

    @ApiOperation("修改菜单显示状态")
    @PostMapping("/updateHidden/{id}")
    public ResponseResult updateHidden(@PathVariable("id") long id, @RequestParam int hidden) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);
        updateWrapper.set("hidden", hidden);
        menuService.update(updateWrapper);
        return new ResponseResult(200,"修改成功");
    }
}
