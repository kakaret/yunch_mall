package com.zrrd.yunchmall.user.controller;

import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.user.service.IAdminService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "权限服务-管理员模块")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "管理员登录", httpMethod = "POST")
    @RequestMapping("/login")
    public ResponseResult login(@ApiParam(required = true) @RequestBody Admin admin) {
        String token = adminService.login(admin.getUsername(), admin.getPassword());
        if(token != null) {
            return new ResponseResult<>(200, "登录成功", token);
        }
        return new ResponseResult<>(401, "用户名或密码错误", null);
    }

    @RequestMapping("/testSleuth")
    public String testSleuth(){
        return  "用户：测试链路追踪";
    }
}
