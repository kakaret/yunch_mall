package com.zrrd.yunchmall.user.service;

import com.zrrd.yunchmall.user.entity.Menu;
import com.zrrd.yunchmall.user.entity.Resource;
import com.zrrd.yunchmall.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
public interface IRoleService extends IService<Role> {

    List<Resource> listResource(long roleId);

    void allocMenu(String roleId, String menuIds);


    void allocResource(String roleId, String resourceIds);

    List<Menu> listMenu(long roleId);
}
