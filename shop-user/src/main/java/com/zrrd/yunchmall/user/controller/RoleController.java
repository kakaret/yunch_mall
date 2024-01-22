package com.zrrd.yunchmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.user.entity.*;
import com.zrrd.yunchmall.user.service.*;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
// controller第一步修改注释，第二删掉/user
@RestController//1
@RequestMapping("/role")//2
@Api(tags = "权限服务-角色模块")//3
public class RoleController {
    //4
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleMenuRelationService roleMenuRelationService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IRoleResourceRelationService roleResourceRelationService;

    @ApiOperation("查询所有角色信息")
    @GetMapping("/listAll")
    public ResponseResult listAll() {
        return new ResponseResult(200,"查询成功", roleService.list());
    }

    @ApiOperation("查询角色列表")
    @GetMapping("/list")
    public ResponseResult list(@ApiParam(required = true,defaultValue = "1") @RequestParam int pageSize,
                               @ApiParam(required = true,defaultValue = "10") @RequestParam int pageNum,
                               @ApiParam @RequestParam(required = false) String keyword) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {//当关键词不为空
            queryWrapper.eq("name", keyword).or().eq("name", keyword);
        }
        IPage<Role> pageInfo = roleService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return new ResponseResult(200, "查询成功", pageInfo);
    }
    @ApiOperation("添加一个角色")
    @PostMapping("/create")
    public ResponseResult create(@RequestBody Role role) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",role.getName());

        if (roleService.count(queryWrapper) > 0) {
            return new ResponseResult(205,"添加失败，角色已存在！");
        }
        role.setCreateTime(LocalDateTime.now());
        roleService.save(role);
        return new ResponseResult(200,"添加成功");
    }

    @ApiOperation("修改角色信息")
    @PostMapping("/update/{id}")
    public ResponseResult update(@RequestBody Role role, @PathVariable("id") long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);// where 字句
        roleService.update(role,updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("删除角色")
    @PostMapping("/delete")
    public ResponseResult delete(long ids) {
        roleService.removeById(ids);
        return new ResponseResult(200,"删除成功");
    }

    @ApiOperation("修改角色状态")
    @PostMapping("/updateStatus/{id}")
    public ResponseResult updateStatus(@PathVariable("id") long id, @RequestParam int status) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);
        updateWrapper.set("status", status);
        roleService.update(updateWrapper);
        return new ResponseResult(200,"修改成功");
    }

    @ApiOperation("查询管理员菜单")
    @GetMapping("/listMenu/{roleId}")
    public ResponseResult listMenuByRole(@PathVariable("roleId") long roleId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        List<RoleMenuRelation> roleMenuRelationList = roleMenuRelationService.list(queryWrapper);
        List<Menu> menuList = new ArrayList<>();
        if(roleMenuRelationList != null) {
            for (int i = 0; i < roleMenuRelationList.size(); i++) {
                Menu menu = menuService.getById(roleMenuRelationList.get(i).getMenuId());
                menuList.add(menu);
            }
        }
        return new ResponseResult(200,"查询成功", menuList);
    }

    @ApiOperation("分配管理员菜单")
    @PostMapping("/allocMenu")//路径可在前端看
    public ResponseResult allocMenu(@RequestParam String roleId, @RequestParam String menuIds) {//参数可在网页检查网络载荷中查看
        roleService.allocMenu(roleId,menuIds);
        return new ResponseResult(200,"修改成功");
    }


    @ApiOperation("查询管理员资源")
    @GetMapping("/listResource/{roleId}")
    public ResponseResult listResourceByRole(@PathVariable("roleId") long roleId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        List<RoleResourceRelation> roleResourceRelationList = roleResourceRelationService.list(queryWrapper);
        List<Resource> resourceList = new ArrayList<>();
        if(roleResourceRelationList != null) {
            for (int i = 0; i < roleResourceRelationList.size(); i++) {
                Resource resource = resourceService.getById(roleResourceRelationList.get(i).getResourceId());
                resourceList.add(resource);
            }
        }
        return new ResponseResult(200,"查询成功", resourceList);
    }

    @ApiOperation("分配管理员资源")
    @PostMapping("/allocResource")//路径可在前端看
    public ResponseResult allocResource(@RequestParam String roleId, @RequestParam String resourceIds) {//参数可在网页检查网络载荷中查看
        roleService.allocResource(roleId,resourceIds);
        return new ResponseResult(200,"修改成功");
    }
}
