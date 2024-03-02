package com.zrrd.yunchmall.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrrd.yunchmall.config.RabbitConfig;
import com.zrrd.yunchmall.order.entity.OrderReturnApply;
import com.zrrd.yunchmall.order.service.IOrderReturnApplyService;
import com.zrrd.yunchmall.user.entity.Admin;
import com.zrrd.yunchmall.util.JwtUtil;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 订单退货申请 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/returnApply")
@Api(tags = "订单服务-退货申请模块")
public class OrderReturnApplyController {

    @Autowired
    private IOrderReturnApplyService orderReturnApplyService;
    private RabbitTemplate rabbitTemplate; //注入一个rabbit模板实例用来发布消息

    @ApiOperation("查询单个退货申请")
    @GetMapping("/{id}")
    public ResponseResult getOne(@PathVariable("id") long id) {
        return new ResponseResult(200, "查询成功", orderReturnApplyService.getById(id));
    }

    @ApiOperation("查询退货申请列表")
    @GetMapping("/list")
    public ResponseResult list(@RequestParam int pageNum, @RequestParam int pageSize,
                               Integer id,
                               Integer status,
                               String createTime,
                               String handleMan,
                               String handleTime) {
        QueryWrapper<OrderReturnApply> queryWrapper = new QueryWrapper();
        if(id != null) {
            queryWrapper.eq("id", id);
        } else {
            if (status != null) {
                queryWrapper.eq("status", status);
            }
            if (!StringUtils.isEmpty(createTime)) {
                queryWrapper.between("create_time", createTime + " 00:00:00", createTime + " 23:59:59");
            }
            if (!StringUtils.isEmpty(handleMan)) {
                queryWrapper.like("handle_man", handleMan);
            }
            if (!StringUtils.isEmpty(handleTime)) {
                queryWrapper.between("handle_time", handleTime + " 00:00:00", handleTime + " 23:59:59");
            }
        }
        return new ResponseResult(200, "查询成功", orderReturnApplyService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @ApiOperation("批量删除")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam String ids) {
        orderReturnApplyService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return new ResponseResult(200, "删除成功");
    }

    @ApiOperation("处理退货订单")
    @PostMapping("/update/status/{id}")
    public ResponseResult status(@PathVariable("id") long id, @RequestBody OrderReturnApply orderReturnApply, @RequestHeader("Authorization") String token){
        Admin admin = JwtUtil.parseAdminToken(token.substring(7));
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", id);
        updateWrapper.set("status", orderReturnApply.getStatus());
        updateWrapper.set("handle_time", LocalDateTime.now());
        updateWrapper.set("handle_man", admin.getUsername());
        updateWrapper.set("handle_note", orderReturnApply.getHandleNote());
        if(orderReturnApply.getStatus() == 1) { //同意退货
            updateWrapper.set("return_amount", orderReturnApply.getReturnAmount());
            updateWrapper.set("company_address_id", orderReturnApply.getCompanyAddressId());
        }
        if(orderReturnApply.getStatus() == 2){ //确实收到退货
//            生成一个mq消息 通知商品服务修改商品库存
            Map map = new HashMap();
            map.put("info", "退货修改库存");
            map.put("productId", orderReturnApply.getProductId());
            map.put("productCount", orderReturnApply.getProductCount());
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "mall_return", map);
            updateWrapper.set("receive_time", LocalDateTime.now());
            updateWrapper.set("receive_man", admin.getUsername());
            updateWrapper.set("receive_note", orderReturnApply.getReceiveNote());
        }
        orderReturnApplyService.update(updateWrapper);
        return new ResponseResult(200, "处理成功");
    }
}
