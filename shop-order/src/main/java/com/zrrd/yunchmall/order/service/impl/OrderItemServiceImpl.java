package com.zrrd.yunchmall.order.service.impl;

import com.zrrd.yunchmall.order.entity.OrderItem;
import com.zrrd.yunchmall.order.mapper.OrderItemMapper;
import com.zrrd.yunchmall.order.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}
