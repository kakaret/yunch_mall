package com.zrrd.yunchmall.order.service;

import com.zrrd.yunchmall.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

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

    /**
     * 提交订单的方法
     * @param pid 商品Id
     * @param num 购买数量
     * @param memberId 会员id
     * @param memberUsername 会员用户名
     * @param receiverName 收件人姓名
     * @param receiverPhone 收件人电话
     */
    void submitOrder(Long pid, Integer num, Long memberId, String memberUsername, String receiverName, String receiverPhone);

    void closeOrder(String ids, String note, String token);

    void deliveryOrder(List<Order> orderList, String token);

    /**
     * 执行批量删除订单的操作 同时记录操作日志
     * @param ids 待删除的订单id列表 使用多个逗号分割
     * @param token token
     */
    void remove(String ids, String token);

    void updateReceiverInfo(Order order, String token);

    void moneyInfo(Order order, String token);
}
