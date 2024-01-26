package com.zrrd.yunchmall.order.service;

import com.zrrd.yunchmall.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author JGX
 * @since 2024-01-16
 */
public interface IOrderService extends IService<Order> {

    void closeOrder(String ids, String note, String token);

    void deliveryOrder(List<Order> orderList, String token);

    /**
     * 执行批量删除订单的操作 同时记录操作日志
     * @param ids 待删除的订单id列表 使用多个逗号分割
     * @param token token
     */
    void remove(String ids, String token);

    void updateReceiverInfo(Order order, String token);
}
