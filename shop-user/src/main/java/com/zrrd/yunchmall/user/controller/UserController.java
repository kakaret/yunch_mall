package com.zrrd.yunchmall.user.controller;

import com.zrrd.yunchmall.pojo.UserTmp;
import com.zrrd.yunchmall.user.service.impl.UserServiceImpl;
import com.zrrd.yunchmall.user.util.JwtUtil;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
//@Api添加在Controller类上 每个类加一次
@Api(tags = "用户服务接口")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/{uid}")
//    @ApiOperation加在请求处理方法上 每个方法加一次
    @ApiOperation(value = "查询用户详情", httpMethod = "GET")
//    @ApiParam加在请求参数上的，每个参数加一次 (name默认就是参数名)
    public UserTmp userInfo(@ApiParam(name = "uid", value = "用户ID", required = true) @PathVariable("uid") int uid) {
        return userService.getById(uid);
    }

//    http://localhost:8061/user/login?username=&password=
    @RequestMapping("/login")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public ResponseResult<String> login(@ApiParam(value = "用户名", required = true) @RequestParam("username") String username,
                                        @ApiParam(value = "密码", required = true) @RequestParam("password") String password) {
        String token = userService.login(username, password);
        if (token != null) { //登陆成功
            return new ResponseResult<>(200, "登录成功", token);
        }
        return new ResponseResult<>(401, "用户名或密码错误", null);
    }
    @RequestMapping("/login2")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public ResponseResult<String> login2(@ApiParam(required = true) @RequestBody UserTmp userTmp) {
        String token = userService.login(userTmp.getUsername(), userTmp.getPassword());
        if (token != null) { //登陆成功
            return new ResponseResult<>(200, "登录成功", token);
        }
        return new ResponseResult<>(401, "用户名或密码错误", null);
    }

    @RequestMapping("/auth")
    public UserTmp auth(String token) {
//        去掉token前面的Bearer+空格
        return JwtUtil.parse(token.substring(7));
    }
}

