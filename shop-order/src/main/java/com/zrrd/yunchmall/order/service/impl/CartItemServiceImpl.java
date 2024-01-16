package com.zrrd.yunchmall.order.service.impl;

import com.zrrd.yunchmall.order.entity.CartItem;
import com.zrrd.yunchmall.order.mapper.CartItemMapper;
import com.zrrd.yunchmall.order.service.ICartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-16
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements ICartItemService {

}
