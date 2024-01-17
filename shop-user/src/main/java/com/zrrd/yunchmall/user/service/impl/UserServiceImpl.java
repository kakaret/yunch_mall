package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.pojo.UserTmp;
import com.zrrd.yunchmall.user.mapper.UserMapper;
import com.zrrd.yunchmall.user.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserTmp> {

    /**
     * 执行登录认证的方法
     * @param username 用户名
     * @param password 密码
     * @return 认证通过则返回jwt令牌 失败则返回null
     */
    public String login(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
//        select uid,username,password...from t_user where username=xiaohua
        UserTmp userTmp = getOne(queryWrapper);
        if(userTmp != null) {
//            检查密码是否正确
            if(BCrypt.checkpw(password, userTmp.getPassword())) {
                return JwtUtil.create(30 * 60 * 1000, userTmp);
//                return userTmp;
            }
        }
        return null; //用户名或密码错误 返回空
    }
}
