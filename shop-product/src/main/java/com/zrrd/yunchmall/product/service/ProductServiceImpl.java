package com.zrrd.yunchmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrrd.yunchmall.pojo.ProductTmp;
import com.zrrd.yunchmall.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductTmp> {

}
