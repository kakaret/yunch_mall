package com.zrrd.yunchmall.user.service;

import com.zrrd.yunchmall.user.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrrd.yunchmall.user.entity.Role;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 管理员登录的业务方法
     * @param username 用户名（登录名）
     * @param password 密码
     * @return JWT生成的 token
     */
    String login(String username,String password);

    /**
     * 向管理员对象中注入权限信息，角色列表和菜单列表
     * @param admin
     * @return*/
    Admin setPermissionInfo(Admin admin);

     /**
      * 返回管理员所拥有的角色列表
      * @param id 管理员ID
      * @return
      * */
    List<Role> getRoleList(long id);

    /**
     * 给管理员分配新的角色
     * @param adminId 管理员id
     * @param roleIds 角色列表：id1，id2，id3
     *
     * */
    void allocRole(long adminId, String roleIds);
}
