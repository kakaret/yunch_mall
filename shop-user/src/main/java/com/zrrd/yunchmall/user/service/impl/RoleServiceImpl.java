package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.user.entity.*;
import com.zrrd.yunchmall.user.mapper.RoleMapper;
import com.zrrd.yunchmall.user.mapper.RoleMenuRelationMapper;
import com.zrrd.yunchmall.user.mapper.RoleResourceRelationMapper;
import com.zrrd.yunchmall.user.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMenuRelationMapper roleMenuRelationMapper;

    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    @Autowired
    private IRoleMenuRelationService roleMenuRelationService;

    @Autowired
    private IRoleResourceRelationService roleResourceRelationService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IResourceService resourceService;
//    你想要缓存返回结果的方法都可以使用这个注解 也就是添加上了@Cacheable的方法 返回值会保存到redis缓存中
//    Role：listMenu_5


    @Override
    @Cacheable(value = "Role", key = "#root + '_' + #roleId")
    public List<Menu> listMenu(long roleId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        List<RoleMenuRelation> roleMenuRelationList = roleMenuRelationService.list(queryWrapper);
        List<Menu> menuList = new ArrayList<>();
        if (roleMenuRelationList != null) {
            for (int i = 0; i < roleMenuRelationList.size(); i++) {
                Menu menu = menuService.getById(roleMenuRelationList.get(i).getMenuId());
                menuList.add(menu);
            }
        }
        return menuList;
    }

    @Override
    @Cacheable(value = "Role", key = "#root.args[0]")
    public List<Resource> listResource(long roleId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        List<RoleResourceRelation> roleResourceRelationList = roleResourceRelationService.list(queryWrapper);
        List<Resource> resourceList = new ArrayList<>();
        if (roleResourceRelationList != null) {
            for (int i = 0; i < roleResourceRelationList.size(); i++) {
                Resource resource = resourceService.getById(roleResourceRelationList.get(i).getResourceId());
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

    @Override
    @Transactional
    public void allocMenu(String roleId, String menuIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        // delete from ums_admin_role_relation where admin_id=? 生成sql语句用了 动态代理 设计模式
        roleMenuRelationMapper.delete(queryWrapper);
        //2.分配当前全部角色
        if (!StringUtils.isEmpty(menuIds)) {
            for (String menuId : menuIds.split(",")) {
                RoleMenuRelation relation = new RoleMenuRelation();//封装relation对象
                relation.setRoleId(Long.valueOf(roleId));
                relation.setMenuId(Long.valueOf(menuId));
                roleMenuRelationMapper.insert(relation);//添加这个relation
            }
        }
    }

    @Override
    @Transactional
    public void allocResource(String roleId, String resourceIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);
        // delete from ums_admin_role_relation where admin_id=? 生成sql语句用了 动态代理 设计模式
        roleResourceRelationMapper.delete(queryWrapper);
        //2.分配当前全部角色
        if (!StringUtils.isEmpty(resourceIds)) {
            for (String resourceId : resourceIds.split(",")) {
                RoleResourceRelation relation = new RoleResourceRelation();//封装relation对象
                relation.setRoleId(Long.valueOf(roleId));
                relation.setResourceId(Long.valueOf(resourceId));
                roleResourceRelationMapper.insert(relation);//添加这个relation
            }
        }
    }


}
