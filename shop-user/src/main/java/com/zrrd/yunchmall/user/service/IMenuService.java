package com.zrrd.yunchmall.user.service;

import com.zrrd.yunchmall.user.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getTreeList();
}
