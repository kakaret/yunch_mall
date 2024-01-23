package com.zrrd.yunchmall.order.controller;

import com.zrrd.yunchmall.order.service.ICompanyAddressService;
import com.zrrd.yunchmall.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公司收发货地址表 前端控制器
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/companyAddress")
@Api(tags = "订单服务-公司地址模块")
public class CompanyAddressController {
    @Autowired
    private ICompanyAddressService companyAddressService;
    @ApiOperation("查询地址列表")
    @GetMapping("/list")
    public ResponseResult list() {
        return new ResponseResult(200, "查询成功", companyAddressService.list());
    }

}
