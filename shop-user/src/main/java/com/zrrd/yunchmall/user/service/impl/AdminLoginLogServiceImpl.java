package com.zrrd.yunchmall.user.service.impl;

import com.zrrd.yunchmall.user.entity.AdminLoginLog;
import com.zrrd.yunchmall.user.mapper.AdminLoginLogMapper;
import com.zrrd.yunchmall.user.service.IAdminLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户登录日志表 服务实现类
 * </p>
 *
 * @author LiYe
 * @since 2024-01-15
 */
@Service
public class AdminLoginLogServiceImpl extends ServiceImpl<AdminLoginLogMapper, AdminLoginLog> implements IAdminLoginLogService {

}
