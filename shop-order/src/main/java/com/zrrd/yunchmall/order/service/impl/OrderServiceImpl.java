package com.zrrd.yunchmall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.order.client.CouponServiceClient;
import com.zrrd.yunchmall.order.client.ProductServiceClient;
import com.zrrd.yunchmall.order.entity.Order;
import com.zrrd.yunchmall.order.entity.OrderOperateHistory;
import com.zrrd.yunchmall.order.mapper.OrderMapper;
import com.zrrd.yunchmall.order.mapper.OrderOperateHistoryMapper;
import com.zrrd.yunchmall.order.service.IOrderItemService;
import com.zrrd.yunchmall.order.service.IOrderService;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    //    注入一个商品服务的客户端 便于远程调用商品服务的接口
    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private OrderOperateHistoryMapper historyMapper;

    @Autowired
    private IOrderItemService itemService;

    @Autowired
    private CouponServiceClient couponServiceClient;

    @Override
//    只能用于管理本地事务 第3步需要跨服务完成，会产生分布式事务问题（找Seata帮忙）
    @Transactional
//    @GlobalTransactional
    public void closeOrder(String ids, String note, String token) {
        // 1.修改订单状态
        List<Order> orderList = super.listByIds(Arrays.asList(ids.split(",")));
        orderList.forEach(order -> {
//            将订单状态改成4->已关闭
            order.setStatus(4);
            // 2.记录日志
            OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
            orderOperateHistory.setOrderId(order.getId());
            orderOperateHistory.setOrderStatus(4);
            orderOperateHistory.setNote(note);
            orderOperateHistory.setOperateMan(JwtUtil.parseAdminToken(token.substring(7)).getNickName());
            historyMapper.insert(orderOperateHistory);

            List<Map<String, Object>> params = new LinkedList<>();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.select("product_id as productId", "product_sku_id as skuId", "product_quantity as quantity");
            queryWrapper.eq("order_id", order.getId());
            params = itemService.listMaps(queryWrapper);
//            远程调用商品服务 释放内存
            productServiceClient.freeStock(params);
//            释放优惠券
            couponServiceClient.freeCoupon(order.getId());
        });
//        执行一个批量更新
        super.updateBatchById(orderList);
        // 3.释放库存
    }

    @Override
    @Transactional
    public void deliveryOrder(List<Order> orderList, String token) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        orderList.forEach(order -> {
            updateWrapper.clear();
//            添加物流公司
            updateWrapper.set("delivery_company", order.getDeliveryCompany());
//            添加物流单号
            updateWrapper.set("delivery_sn", order.getDeliverySn());
//            添加发货时间
            updateWrapper.set("delivery_time", LocalDateTime.now());
//            将订单状态改成2(已发货)
            updateWrapper.set("status", 2);
            updateWrapper.eq("id", order.getOrderId());
            super.update(updateWrapper);

            Admin admin = JwtUtil.parseAdminToken(token.substring(7));
            OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
            orderOperateHistory.setOrderId(order.getOrderId());
            orderOperateHistory.setOperateMan(admin.getNickName());
            orderOperateHistory.setOrderStatus(order.getStatus());
            orderOperateHistory.setNote("订单发货");
            historyMapper.insert(orderOperateHistory);

        });
    }

    @Override
    @Transactional
    public void remove(String ids, String token) {
//        1.遍历订单
//        2.删除满足条件的订单
//        3.记录操作日志
        List<String> idsList = Arrays.asList(ids.split(","));
        Admin admin = JwtUtil.parseAdminToken(token.substring(7));
        idsList.forEach(id -> {

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.select("status");
            queryWrapper.eq("id", id);
            Order order = super.getOne(queryWrapper);
//            订单存在 且状态满足删除条件
            if (order != null && (order.getStatus() == 4 || order.getStatus() == 5)) {
//                删除满足状态的订单
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.set("delete_status", 1);
                updateWrapper.eq("id", id);
                super.update(updateWrapper);

//                记录操作日志
                OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
                orderOperateHistory.setOrderId(Long.valueOf(id));
                orderOperateHistory.setOperateMan(admin.getNickName());
                orderOperateHistory.setOrderStatus(6);
                orderOperateHistory.setNote("删除订单!");
                historyMapper.insert(orderOperateHistory);
            }
        });
    }

    @Override
    @Transactional
    public void updateReceiverInfo(Order order, String token) {

        UpdateWrapper<Order> updateWrapper = new UpdateWrapper();
        updateWrapper.set("receiver_city", order.getReceiverCity());
        updateWrapper.set("receiver_detail_address", order.getReceiverDetailAddress());
        updateWrapper.set("receiver_name", order.getReceiverName());
        updateWrapper.set("receiver_phone", order.getReceiverPhone());
        updateWrapper.set("receiver_post_code", order.getReceiverPostCode());
        updateWrapper.set("receiver_province", order.getReceiverProvince());
        updateWrapper.set("receiver_region", order.getReceiverRegion());
        updateWrapper.eq("id", order.getOrderId());
        super.update(updateWrapper);

        Admin admin = JwtUtil.parseAdminToken(token.substring(7));
        OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
        orderOperateHistory.setOrderId(order.getOrderId());
        orderOperateHistory.setOperateMan(admin.getNickName());
        orderOperateHistory.setOrderStatus(order.getStatus());
        orderOperateHistory.setNote("修改收货人地址！");
        historyMapper.insert(orderOperateHistory);
    }

    @Override
    @Transactional
    public void moneyInfo(Order order, String token) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", order.getOrderId());
        Order order1 = super.getOne(queryWrapper);
        if (order.getDiscountAmount() != null)
            order1.setDiscountAmount(order.getDiscountAmount());
        if (order.getFreightAmount() != null)
            order1.setFreightAmount(order.getFreightAmount());
        //修改订单金额
        order1.setPayAmount(order1.getTotalAmount()
                .add(order1.getFreightAmount())
                .subtract(order1.getCouponAmount())
                .subtract(order1.getIntegrationAmount())
                .subtract(order1.getPromotionAmount())
                .subtract(order1.getDiscountAmount())
        );
        super.updateById(order1);

        Admin admin = JwtUtil.parseAdminToken(token.substring(7));
        OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
        orderOperateHistory.setOrderId(order.getOrderId());
        orderOperateHistory.setOperateMan(admin.getNickName());
        orderOperateHistory.setOrderStatus(order.getStatus());
        orderOperateHistory.setNote("修改订单费用信息！");
        historyMapper.insert(orderOperateHistory);
    }

}
