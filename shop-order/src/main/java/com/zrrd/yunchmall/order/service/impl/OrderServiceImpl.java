package com.zrrd.yunchmall.order.service.impl;

import com.zrrd.yunchmall.order.entity.Order;
import com.zrrd.yunchmall.order.mapper.OrderMapper;
import com.zrrd.yunchmall.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
