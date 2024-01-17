package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.pojo.UserTmp;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.user.mapper.AdminMapper;
import com.zrrd.yunchmall.user.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.user.util.JwtUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Override
    public String login(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
//        select uid,username,password...from t_user where username=xiaohua
        Admin admin = getOne(queryWrapper);
        if(admin != null) {
//            检查密码是否正确
            if(BCrypt.checkpw(password, admin.getPassword())) {
                return JwtUtil.create(30 * 60 * 1000, admin);
//                return userTmp;
            }
        }
        return null; //用户名或密码错误 返回空
    }

}
