package com.zrrd.yunchmall.user.controller;

import com.zrrd.yunchmall.pojo.UserTmp;
import com.zrrd.yunchmall.user.service.UserServiceImpl;
import com.zrrd.yunchmall.user.util.JwtUtil;
import com.zrrd.yunchmall.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/{uid}")
    public UserTmp userInfo(@PathVariable("uid") int uid) {
        return userService.getById(uid);
    }

//    http://localhost:8061/user/login?username=&password=
    @RequestMapping("/login")
    public ResponseResult<String> login(String username, String password) {
        String token = userService.login(username, password);
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

