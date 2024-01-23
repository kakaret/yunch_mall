package com.zrrd.yunchmall.user.service.impl;

import com.zrrd.yunchmall.user.entity.AdminPermissionRelation;
import com.zrrd.yunchmall.user.mapper.AdminPermissionRelationMapper;
import com.zrrd.yunchmall.user.service.IAdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Service
public class AdminPermissionRelationServiceImpl extends ServiceImpl<AdminPermissionRelationMapper, AdminPermissionRelation> implements IAdminPermissionRelationService {

}
