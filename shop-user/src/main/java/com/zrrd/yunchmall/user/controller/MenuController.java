package com.zrrd.yunchmall.user.controller;

import com.zrrd.yunchmall.user.entity.Menu;
import com.zrrd.yunchmall.user.service.IMenuService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台菜单表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "权限服务-菜单模块")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "查询菜单树")
    @GetMapping("/treeList")
    public ResponseResult treeList() {
        List<Menu> treeList = menuService.getTreeList();
        return new ResponseResult(200, "查询成功", treeList);
    }
}
