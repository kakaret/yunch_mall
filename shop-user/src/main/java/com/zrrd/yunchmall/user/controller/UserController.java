package com.zrrd.yunchmall.user.controller;

import com.zrrd.yunchmall.pojo.UserTmp;
import com.zrrd.yunchmall.user.service.impl.UserServiceImpl;
import com.zrrd.yunchmall.util.JwtUtil;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
// @Api添加在Controller类上，每个类加一次
@Api(value = "用户服务接口", tags = "用户服务接口")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/{uid}")
    //  @ApiOperation加载请求处理方法上，每个方法加一次
//    @ApiOperation(value = "查询用户详情")
    @ApiOperation(value = "查询用户详情", httpMethod = "GET")
    // @ApiParam加在请求参数上的，每个参数加一次
    public UserTmp detail(@ApiParam(name = "uid", value = "用户ID", required = true) @PathVariable("uid") int uid) {
        return userService.getById(uid);
    }
    // http://localhost:8061/user/login?username=admin&password=123456
    @RequestMapping("/login")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public ResponseResult<String> login(@ApiParam(name = "username", value = "用户名", required = true)
                                                    @RequestParam("username") String username,
                                        @ApiParam(name = "password", value = "密码", required = true)
                                                    @RequestParam("password") String password) {
        String token = userService.login(username, password);
        //登录成功
        if (token != null) {
            return new ResponseResult<>(200, "登陆成功", token);
        } else {
            return new ResponseResult<>(401, "用户名或密码错误", null);
        }
    }

    @RequestMapping("/login2")
    @ApiOperation(value = "用户登录2", httpMethod = "POST")
    // {"username": "admin", "password": "123456"}
    public ResponseResult<String> login2(@ApiParam(required = true) @RequestBody UserTmp userTmp) {
        String token = userService.login(userTmp.getUsername(), userTmp.getPassword());
        //登录成功
        if (token != null) {
            return new ResponseResult<>(200, "登陆成功", token);
        } else {
            return new ResponseResult<>(401, "用户名或密码错误", null);
        }
    }
    @RequestMapping("/auth")
    public UserTmp auth(String token) {
        UserTmp userTmp = JwtUtil.parse(token.substring(7));
        // 去掉token前面的 Bearer+空格
        return userTmp;
    }
}
