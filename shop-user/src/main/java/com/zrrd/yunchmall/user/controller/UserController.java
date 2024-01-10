package com.zrrd.yunchmall.user.controller;

import com.zrrd.yunchmall.pojo.UserTmp;
import com.zrrd.yunchmall.user.service.UserServiceImpl;
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
}
