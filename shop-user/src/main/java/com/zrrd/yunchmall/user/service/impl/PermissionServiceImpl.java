package com.zrrd.yunchmall.user.service.impl;

import com.zrrd.yunchmall.user.entity.Permission;
import com.zrrd.yunchmall.user.mapper.PermissionMapper;
import com.zrrd.yunchmall.user.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
