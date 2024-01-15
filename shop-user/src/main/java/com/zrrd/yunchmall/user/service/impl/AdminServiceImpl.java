package com.zrrd.yunchmall.user.service.impl;

import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.user.mapper.AdminMapper;
import com.zrrd.yunchmall.user.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
