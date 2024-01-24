package com.zrrd.yunchmall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.user.entity.Role;
import com.zrrd.yunchmall.user.service.IAdminService;
import com.zrrd.yunchmall.util.JwtUtil;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Update;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "权限服务-管理员模块")
public class AdminController {

    @Autowired
    private IAdminService adminService;


    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public ResponseResult login(@ApiParam(required = true) @RequestBody Admin admin) {
        String token = adminService.login(admin.getUsername(), admin.getPassword());
        if (token != null ) {
            return new ResponseResult(200,"登录成功", token);
        } else{
            return new ResponseResult(401,"用户名或密码错误",null);
        }
    }

    @ApiOperation("获取管理员信息")
    @GetMapping("/info")
    public ResponseResult<Admin> info(@RequestHeader("Authorization") String token) {
        // 解析令牌返回一个封装了id和用户名的Admin对象
        Admin admin = JwtUtil.parseAdminToken(token.substring(7)); //跳过token前面的Bearer + 空格
        admin = adminService.setPermissionInfo(admin);
        return new ResponseResult<>(200,"查询成功",admin);
    }

    @ApiOperation("管理员登出")
    @PostMapping("/logout")
    public ResponseResult logout() {

        return new ResponseResult(200,"登出成功",null);
    }

    @ApiOperation("查询管理员列表")
    @GetMapping("/list")
    public ResponseResult list(@ApiParam(required = true,defaultValue = "1") @RequestParam int pageSize,
                               @ApiParam(required = true,defaultValue = "10") @RequestParam int pageNum,
                               @ApiParam @RequestParam(required = false) String keyword) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {//当关键词不为空
            queryWrapper.eq("username",keyword).or().eq("nick_name",keyword);
        }
        IPage<Admin> pageInfo = adminService.page(new Page<>(pageNum,pageSize), queryWrapper);
        return new ResponseResult(200,"查询成功", pageInfo);
    }

    @ApiOperation("注册一个管理员")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody Admin admin) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", admin.getUsername());

        if (adminService.count(queryWrapper) > 0) {
            return new ResponseResult(205,"注册失败：用户名已存在");//查询进行不下去，而不是查询失败
        }
        //密码明文
        String passText = admin.getPassword();
        // 使用Spring 自带加密工具对管理员的密码进行加密
        String secretPass = BCrypt.hashpw(passText, BCrypt.gensalt());
        // 将管理员密码修改成加密之后
        admin.setPassword(secretPass);
        // 设置创建时间
        admin.setCreateTime(LocalDateTime.now());
        // 在数据库里保存这个管理员对象
        adminService.save(admin);
        return new ResponseResult(200,"注册成功");
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update/{id}")
    public ResponseResult update(@RequestBody Admin admin, @PathVariable("id") long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);// where 字句
        adminService.update(admin,updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("删除管理员账号")
    @PostMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") long id) {
        adminService.removeById(id);
        return new ResponseResult(200,"删除成功");
    }

    @ApiOperation("修改管理员状态")
    @PostMapping("/updateStatus/{id}")
    public ResponseResult updateStatus(@PathVariable("id") long id, @RequestParam int status) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);
        updateWrapper.set("status", status);
        adminService.update(updateWrapper);
        return new ResponseResult(200,"修改成功");
    }

    @ApiOperation("查询管理员现有角色")
    @GetMapping("/role/{id}")
    public ResponseResult role(@PathVariable("id") long id) {
        List<Role> roles = adminService.getRoleList(id);
        return new ResponseResult(200,"查询成功",roles);
    }

    @ApiOperation("分配管理员角色")
    @PostMapping("/role/update")//路径可在前端看
    public ResponseResult allocRole(@RequestParam long adminId, @RequestParam String roleIds) {//参数可在网页检查网络载荷中查看
        adminService.allocRole(adminId,roleIds);
        return new ResponseResult(200,"修改成功");
    }

    @GetMapping("/testSleuth")
    public String testSleuth() {
        return "管理员：测试链路追踪";
    }
}
