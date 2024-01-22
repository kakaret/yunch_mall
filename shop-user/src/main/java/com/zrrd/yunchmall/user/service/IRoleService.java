package com.zrrd.yunchmall.user.service;

import com.zrrd.yunchmall.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
public interface IRoleService extends IService<Role> {

    void allocMenu(String roleId, String menuIds);


    void allocResource(String roleId, String resourceIds);
}
