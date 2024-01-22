package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.user.entity.Menu;
import com.zrrd.yunchmall.user.mapper.MenuMapper;
import com.zrrd.yunchmall.user.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {



    @Override
    public List<Menu> getTreeList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",0);
        queryWrapper.or();
        queryWrapper.eq("level",0);
        //查询到全部一级菜单
        List<Menu> l1MenuList = super.list(queryWrapper);
        // 循环遍历一级菜单列表
        l1MenuList.forEach(menu-> {
           // 查到每个一级菜单的所有二级菜单列表
           // 清除之前的查询条件
           queryWrapper.clear();
           queryWrapper.eq("parent_id",menu.getId());
           menu.setChildren(super.list(queryWrapper));
        });
        return l1MenuList;
    }


}
