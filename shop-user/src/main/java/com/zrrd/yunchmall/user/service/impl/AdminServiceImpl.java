package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.user.entity.AdminRoleRelation;
import com.zrrd.yunchmall.user.entity.Role;
import com.zrrd.yunchmall.user.mapper.AdminMapper;
import com.zrrd.yunchmall.user.mapper.AdminRoleRelationMapper;
import com.zrrd.yunchmall.user.mapper.MenuMapper;
import com.zrrd.yunchmall.user.mapper.RoleMapper;
import com.zrrd.yunchmall.user.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private AdminRoleRelationMapper adminRoleRelationMapper;

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

    /**
     * 注入管理员的角色和菜单列表
     * @param admin
     * @return
     */
    @Override
    public Admin setPermissionInfo(Admin admin) {
        admin = getById(admin.getId());
//        注入角色列表
        admin.setRoles(roleMapper.selectRolesByAdminId(admin.getId()));
//        注入菜单列表
        admin.setMenus(menuMapper.selectMenusByAdminId(admin.getId(), 1));

        return admin;
    }

    @Override
    public List<Role> getRoleListByAdminId(long id) {
        return roleMapper.selectRolesByAdminId(id);
    }

    @Override
    @Transactional //保证这个业务方法里的所有数据库操作都在同一个事务中完成
    public void allocRole(long adminId, String roleIds) {
//        1.移除它原来的角色
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("admin_id", adminId);
//        delete from ums_admin_role_relation where admin_id = ?
        adminRoleRelationMapper.delete(queryWrapper);
//        2.分配当前全部角色
        if(!StringUtils.isEmpty(roleIds)) {
            for (String roleId : roleIds.split(",")) {
                AdminRoleRelation adminRoleRelation = new AdminRoleRelation();
                adminRoleRelation.setAdminId(adminId);
                adminRoleRelation.setRoleId(Long.valueOf(roleId));
                adminRoleRelationMapper.insert(adminRoleRelation); //将封装的relation对象添加到数据库表中
            }
        }
//        3.将1和2置于一个事务当中
    }

}
