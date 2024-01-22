package com.zrrd.yunchmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zrrd.yunchmall.user.entity.AdminRoleRelation;
import com.zrrd.yunchmall.user.entity.Role;
import com.zrrd.yunchmall.user.entity.RoleMenuRelation;
import com.zrrd.yunchmall.user.entity.RoleResourceRelation;
import com.zrrd.yunchmall.user.mapper.RoleMapper;
import com.zrrd.yunchmall.user.mapper.RoleMenuRelationMapper;
import com.zrrd.yunchmall.user.mapper.RoleResourceRelationMapper;
import com.zrrd.yunchmall.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Override
    @Transactional
    public void allocMenu(String roleId, String menuIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",roleId);
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
        queryWrapper.eq("role_id",roleId);
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
