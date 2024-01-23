package com.zrrd.yunchmall.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.order.client.AdminServiceClient;
import com.zrrd.yunchmall.order.client.ProductServiceClient;
import com.zrrd.yunchmall.order.entity.Order;
import com.zrrd.yunchmall.order.service.IOrderService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import net.bytebuddy.implementation.bind.annotation.Pipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
    AdminServiceClient adminService;
    @Autowired
    ProductServiceClient productService;
    @Autowired
    IOrderService orderService;

    @ApiOperation("查询订单列表")
    @GetMapping("/list")
    public ResponseResult list(@RequestParam int pageNum, @RequestParam int pageSize,
                               Integer orderSn,
                               String receiverKeyword,
                               Integer status,
                               Integer orderType,
                               Integer sourceType,
                               String createTime) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(orderSn != null) {
            queryWrapper.like("order_sn", orderSn);
        }
        if(!StringUtils.isEmpty(receiverKeyword)) {
            queryWrapper.like("receiver_keyword", receiverKeyword);
        }
        if(status != null) {
            queryWrapper.eq("status", status);
        }
        if(orderType != null) {
            queryWrapper.eq("order_type", orderType);
        }
        if(sourceType != null) {
            queryWrapper.eq("source_type", sourceType);
        }
        if(!StringUtils.isEmpty(createTime)) {
            queryWrapper.like("create_time", createTime);
        }
        return new ResponseResult(200, "查询成功", orderService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation("查询单个订单信息")
    @GetMapping("/{id}")
    public ResponseResult found(@PathVariable("id") long id) {
        return new ResponseResult(200, "查询成功", orderService.getById(id));
    }

    @ApiOperation("删除订单")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam String ids) {
        orderService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return new ResponseResult(200, "删除成功");
    }

    @ApiOperation("关闭订单")
    @PostMapping("/update/close")
    public ResponseResult close(@RequestParam String ids, @RequestParam String note) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("status", 4);
        updateWrapper.set("note", note);
        updateWrapper.in("id", ids.split(","));
        orderService.update(updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("订单发货")
    @PostMapping("/update/delivery")
    @Transactional
    public ResponseResult delivery(@RequestBody List<Order> orderList) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        orderList.forEach(order -> {
            updateWrapper.clear();
            updateWrapper.set("delivery_company", order.getDeliveryCompany());
            updateWrapper.set("delivery_sn", order.getDeliverySn());
            updateWrapper.set("delivery_time", LocalDateTime.now());
            updateWrapper.eq("id", order.getId());
            orderService.update(updateWrapper);
        });
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("订单备注")
    @PostMapping("/update/note")
    public ResponseResult note(@RequestParam long id, @RequestParam String note, @RequestParam Integer status) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("note", note);
        updateWrapper.eq("id", id);
        orderService.update(updateWrapper);
        return new ResponseResult(200, "修改成功");
    }

    @ApiOperation("修改收货人信息")
    @PostMapping("/update/receiverInfo")
    public ResponseResult receiverInfo(@RequestBody Order order) {
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper();
        updateWrapper.set("receiver_city", order.getReceiverCity());
        updateWrapper.set("receiver_detail_address", order.getReceiverDetailAddress());
        updateWrapper.set("receiver_name", order.getReceiverName());
        updateWrapper.set("receiver_phone", order.getReceiverPhone());
        updateWrapper.set("receiver_post_code", order.getReceiverPostCode());
        updateWrapper.set("receiver_province", order.getReceiverProvince());
        updateWrapper.set("receiver_region", order.getReceiverRegion());
        updateWrapper.eq("id", order.getId());
        orderService.update(updateWrapper);
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
