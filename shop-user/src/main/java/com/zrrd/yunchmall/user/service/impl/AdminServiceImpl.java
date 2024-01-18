package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.user.mapper.AdminMapper;
import com.zrrd.yunchmall.user.mapper.MenuMapper;
import com.zrrd.yunchmall.user.mapper.RoleMapper;
import com.zrrd.yunchmall.user.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Autowired
    private RoleMapper roleMapper; // 在显示无法自动注入依赖的Mapper接口上添加 @Repository 或 @Mapper 注解

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public String login(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
//        状态为0 表示无法登录
        queryWrapper.eq("status", 1);
//        select uid,username,password...from t_user where username=#{param}
        Admin admin = getOne(queryWrapper);
        if(admin != null) {
//            检查密码是否正确
            if(BCrypt.checkpw(password, admin.getPassword())) {
                admin.setLoginTime(LocalDateTime.now());
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.set("login_time", admin.getLoginTime());
                updateWrapper.eq("id", admin.getId());
                super.update(updateWrapper);
                return JwtUtil.create(24 * 30 * 60 * 1000, admin);
//                return userTmp;
            }
        }
        return null; //用户名或密码错误 返回空
    }

    @Override
    public Admin setPermissionInfo(Admin admin) {
        admin = getById(admin.getId());
//        注入角色列表
        admin.setRoles(roleMapper.selectRolesByAdminId(admin.getId()));
//        注入菜单列表
        admin.setMenus(menuMapper.selectMenusByAdminId(admin.getId(), 1));

        return admin;
    }

}
