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
import com.zrrd.yunchmall.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import springfox.documentation.annotations.Cacheable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Service
@SuppressWarnings("all")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private MenuMapper menuMapper;//在显示无法自动注入依赖的Mapper接口上添加@Mapper或@Repository
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleRelationMapper roleRelationMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String login(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //where username=？ and status=？
        queryWrapper.eq("username", username);
        //状态为0表示无法登录
        queryWrapper.eq("status",1);
        // select uid, username, password... from t_user where username=?
        Admin admin = getOne(queryWrapper);
        // userTmp == null 说明用户名不存在
        if (admin != null) {
            //检查密码是否正确
            if (BCrypt.checkpw(password, admin.getPassword())) {
                //更新这个管理员账号的最后登录时间
                admin.setLoginTime(LocalDateTime.now());
                // update ums_admin set login_time = ? where id =?
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.set("login_time", admin.getLoginTime());
                updateWrapper.eq("id",admin.getId());
                super.update(updateWrapper);

                String token = JwtUtil.create(24 * 30 * 60 * 1000, admin);
//                登录成功就将这个token同时存入redis 并设置一个有效时长
//                redisTemplate.opsForSet().add("LOGIN_TOKEN", token);
//                将jwt token的第三部分：签名作为（最后一个小数点之后的部分）key
                String key = "LOGIN_TOKEN_" + token.substring(token.lastIndexOf(".") + 1);
//                将整个token做value
                String value = token;
//                登陆成功 就将生成的token同时存入redis并设置一个有效时长
                redisTemplate.opsForValue().set(key, value);
                redisTemplate.expire(key, 12, TimeUnit.HOURS);
                return token;
            }
        }
        //用户名或密码错误则返回null
        return null;
    }

    @Override
    @Cacheable(value = "Admin")
//    @Cacheable(value = "Role", key = "#root + '_' + #roleId")
//    @Cacheable(value = "Role", key = "#root.args[0]")
    public Admin setPermissionInfo(Admin admin) {
        admin =super.getById(admin.getId());
        // 注入角色列表
        admin.setRoles(roleMapper.selectRolesByAdminId(admin.getId()));
        // 注入菜单列表
        admin.setMenus(menuMapper.selectMenusByAdminId(admin.getId(),1));
        return admin;
    }

    @Override
    public List<Role> getRoleList(long id) {
        return roleMapper.selectRolesByAdminId(id);
    }

    @Override
    @Transactional //保证这个业务方法里的所有数据库操作都在同一个事务中完成
    public void allocRole(long adminId, String roleIds) {
        //注入mapper，在上面
        //1.先移除原来的角色
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("admin_id",adminId);
        // delete from ums_admin_role_relation where admin_id=? 生成sql语句用了 动态代理 设计模式
        roleRelationMapper.delete(queryWrapper);
        //2.分配当前全部角色
        if (!StringUtils.isEmpty(roleIds)) {
            for (String roleId : roleIds.split(",")) {
                AdminRoleRelation relation = new AdminRoleRelation();//封装relation对象
                relation.setAdminId(adminId);
                relation.setRoleId(Long.valueOf(roleId));
                roleRelationMapper.insert(relation);//添加这个relation
            }
        }
        //3.将1和2置于一个事务当中

    }
}
