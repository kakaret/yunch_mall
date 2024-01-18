package com.zrrd.yunchmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.user.entity.Role;
import com.zrrd.yunchmall.user.service.IAdminService;
import com.zrrd.yunchmall.user.service.IRoleService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/role")
@Api(tags = "权限服务-角色模块")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取角色信息", httpMethod = "GET")
    @RequestMapping("/listAll")
    public ResponseResult listAll(){
        List<Role> roleList = roleService.list();
        return new ResponseResult<>(200, "查询成功", roleList);
    }
}
