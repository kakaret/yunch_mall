package com.zrrd.yunchmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.user.service.IAdminService;
import com.zrrd.yunchmall.user.util.JwtUtil;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

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

    @ApiOperation(value = "获取管理员信息", httpMethod = "GET")
    @RequestMapping("/info")
    public ResponseResult<Admin> info(@RequestHeader("Authorization") String token){
//        解析令牌返回一个封装了id和用户名的admin对象
        Admin admin = JwtUtil.parseAdminToken(token.substring(7));
        admin = adminService.setPermissionInfo(admin);
        return new ResponseResult<>(200, "查询成功", admin);
    }

    @ApiOperation(value = "管理员退出", httpMethod = "POST")
    @RequestMapping("/logout")
    public ResponseResult logout(){
//        去Redis里将保存的token清除掉(设置过期)
        return new ResponseResult<>(200, "退出成功", null);
    }

    @ApiOperation(value = "查询管理员列表", httpMethod = "GET")
    @RequestMapping("/list")
    public ResponseResult list(@ApiParam(required = true, defaultValue = "1") @RequestParam int pageSize,
                               @ApiParam(required = true, defaultValue = "10") @RequestParam int pageNum,
                               @ApiParam @RequestParam(required = false) String keyword) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)) {
//            where username=? or nick_name=?
            queryWrapper.eq("username", keyword)
                    .or()
                    .eq("nick_name", keyword);
        }
        IPage<Admin> pageInfo = adminService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return new ResponseResult<>(200, "查询成功", pageInfo);
    }

    @ApiOperation("注册一个管理员")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", admin.getUsername());
        if(adminService.count(queryWrapper) > 0) {
            return new ResponseResult<>(205, "注册失败：用户名已存在");
        }
//        密码的明文
        String passText = admin.getPassword();
//        使用Spring自带的加密工具对管理员的密码进行加密
        String secretText = BCrypt.hashpw(passText, BCrypt.gensalt());
//        在数据库里保存这个管理员记录
        admin.setPassword(secretText);
        admin.setCreateTime(LocalDateTime.now());
        adminService.save(admin);
        return new ResponseResult<>(200, "注册成功");
    }

    /*
    * 1.请求路径a=1&b-2 @requestParam
    * 2.请求路径 /1/2 @PathVariable
    * 3.POST请求 JSON格式封装 {"a":1, "b":2} @RequestBody
    * */
    @ApiOperation("修改用户信息")
    @PostMapping("/update/{id}")
    public ResponseResult update(@RequestBody Admin admin, @PathVariable("id") long id) {
        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        adminService.update(admin, updateWrapper);
        return new ResponseResult<>(200, "修改成功");
    }

    @ApiOperation("删除一个管理员")
    @PostMapping("/delete/{id}")
    public  ResponseResult delete(@PathVariable("id") long id) {
        adminService.removeById(id);
        return new ResponseResult(200, "删除成功");
    }

    @RequestMapping("/testSleuth")
    public String testSleuth(){
        return  "用户：测试链路追踪";
    }
}
