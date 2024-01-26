package com.zrrd.yunchmall.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.order.client.AdminServiceClient;
import com.zrrd.yunchmall.order.client.ProductServiceClient;
import com.zrrd.yunchmall.order.entity.Order;
import com.zrrd.yunchmall.order.entity.OrderOperateHistory;
import com.zrrd.yunchmall.order.service.IOrderItemService;
import com.zrrd.yunchmall.order.service.IOrderOperateHistoryService;
import com.zrrd.yunchmall.order.service.IOrderService;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.util.JwtUtil;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import net.bytebuddy.implementation.bind.annotation.Pipe;
import org.aspectj.weaver.ast.Or;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单服务接口")
public class OrderController {

    @Autowired
    private AdminServiceClient adminService;
    @Autowired
    private ProductServiceClient productService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderOperateHistoryService historyService;
    @Autowired
    private IOrderItemService orderItemService;

    @ApiOperation("查询订单列表")
    @GetMapping("/list")
    public ResponseResult list(@RequestParam int pageNum, @RequestParam int pageSize,
                               String orderSn,
                               String receiverKeyword,
                               Integer status,
                               Integer orderType,
                               Integer sourceType,
                               String createTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("delete_status", 0);
        if(!StringUtils.isEmpty(orderSn)) {
            queryWrapper.eq("order_sn", orderSn);
//            在查询语句中的结尾
            queryWrapper.last(" limit 1");
            List<Order> orderList = orderService.list(queryWrapper);
            IPage page = new Page(pageNum, pageSize);
            page.setTotal(orderList.size());
            page.setPages(orderList.size());
            page.setRecords(orderList);
            return new ResponseResult(200, "查询成功", page);
        } else {
            if (!StringUtils.isEmpty(receiverKeyword)) {
                String regStr = "^1[345678]\\d{9}";
//                正则表达式的API Pattern和Matcher
//                编译正则表达式
                Pattern pattern = Pattern.compile(regStr);
//                使用正则规则去匹配给定的字符串
                Matcher matcher = pattern.matcher(receiverKeyword);
//                提供的关键字是手机号
                if(matcher.matches()) {
                    queryWrapper.eq("receiver_phone", receiverKeyword);
                } else {
                    queryWrapper.eq("receiver_name", receiverKeyword);
                }
            }
            if (status != null) {
                queryWrapper.eq("status", status);
            }
            if (orderType != null) {
                queryWrapper.eq("order_type", orderType);
            }
            if (sourceType != null) {
                queryWrapper.eq("source_type", sourceType);
            }
            if (!StringUtils.isEmpty(createTime)) {
                queryWrapper.between("create_time", createTime + " 00:00:00", createTime + " 23:59:59");
            }
        }
        return new ResponseResult(200, "查询成功", orderService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation("查询单个订单信息")
    @GetMapping("/{id}")
    public ResponseResult detail(@PathVariable("id") long id) {
        Order order = orderService.getById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id", id);
//        查询订单单项列表（商品信息）
        order.setOrderItemList(orderItemService.list(queryWrapper));
//        查询订单的操作历史
        order.setHistoryList(historyService.list(queryWrapper));
        return new ResponseResult(200, "查询成功", order);
    }

    @ApiOperation("删除订单")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam String ids, @RequestHeader("Authorization") String token) {
        orderService.remove(ids, token);
        return new ResponseResult(200, "删除成功");
    }

    @ApiOperation("关闭订单")
    @PostMapping("/update/close")
    public ResponseResult close(@RequestParam String ids, @RequestParam String note, @RequestHeader("Authorization") String token) {
        orderService.closeOrder(ids, note, token);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("订单发货")
    @PostMapping("/update/delivery")
    public ResponseResult delivery(@RequestBody List<Order> orderList, @RequestHeader("Authorization") String token) {
        orderService.deliveryOrder(orderList, token);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("订单备注")
    @PostMapping("/update/note")
    @Transactional
    public ResponseResult note(@RequestParam long id, @RequestParam String note, @RequestParam Integer status, @RequestHeader("Authorization") String token) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("note", note);
        updateWrapper.eq("id", id);
        orderService.update(updateWrapper);

        Admin admin = JwtUtil.parseAdminToken(token.substring(7));
        OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
        orderOperateHistory.setOrderId(id);
        orderOperateHistory.setOperateMan(admin.getNickName());
        orderOperateHistory.setOrderStatus(status);
        orderOperateHistory.setNote("订单备注：" + note);
        historyService.save(orderOperateHistory);

        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("修改收货人信息")
    @PostMapping("/update/receiverInfo")
    public ResponseResult receiverInfo(@RequestBody Order order, @RequestHeader("Authorization") String token) {
        orderService.updateReceiverInfo(order, token);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("修改费用信息")
    @PostMapping("/update/moneyInfo")
    public ResponseResult moneyInfo(@RequestBody Order order, @RequestHeader("Authorization") String token) {
        orderService.moneyInfo(order, token);
        return new ResponseResult(200, "修改成功");
    }

    @RequestMapping("/testSleuth")
    @ApiOperation(value = "测试链路追踪", httpMethod = "GET")
    public String testSleuth() {
//        模拟分别调用商品和管理员服务
        adminService.testSleuth();
        productService.testSleuth();
        return "订单：测试链路追踪";
    }
}
