package com.zrrd.yunchmall.user.service;

import com.zrrd.yunchmall.user.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 管理员登录的业务方法
     * @param username 用户名
     * @param password 密码
     * @return JWT生成的token
     */
    public String login(String username, String password);

    /**
     * 向管理员对象中注入权限信息 角色列表和菜单列表
     * @param admin
     */
    Admin setPermissionInfo(Admin admin);

}
