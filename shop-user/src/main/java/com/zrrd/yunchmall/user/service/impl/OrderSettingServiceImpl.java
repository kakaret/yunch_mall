package com.zrrd.yunchmall.user.service.impl;

import com.zrrd.yunchmall.order.entity.OrderSetting;
import com.zrrd.yunchmall.order.mapper.OrderSettingMapper;
import com.zrrd.yunchmall.order.service.IOrderSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements IOrderSettingService {

}
