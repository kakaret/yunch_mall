package com.zrrd.yunchmall.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/user/admin")
public class AdminController {
    @RequestMapping("/testSleuth")
    public String testSleuth(){
        return  "用户：测试链路追踪";
    }
}
