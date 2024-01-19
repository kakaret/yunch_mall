package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.user.entity.Menu;
import com.zrrd.yunchmall.user.mapper.MenuMapper;
import com.zrrd.yunchmall.user.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getTreeList() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0).or().eq("level", 0);
        List<Menu> l1MenuList = super.list(queryWrapper); //查询到全部的一级菜单
//        循环遍历一级菜单列表
        l1MenuList.forEach(menu -> {
//            查到每个一级菜单的二级菜单列表
            queryWrapper.clear();
            queryWrapper.eq("parent_id", menu.getId());
//            找到menu的菜单的二级菜单
            menu.setChildren(super.list(queryWrapper));
        });
        return l1MenuList;
    }
}
