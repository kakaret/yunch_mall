package com.zrrd.yunchmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zrrd.yunchmall.content.entity.PrefrenceAreaProductRelation;
import com.zrrd.yunchmall.content.entity.SubjectProductRelation;
import com.zrrd.yunchmall.product.client.ContentService;
import com.zrrd.yunchmall.product.entity.*;
import com.zrrd.yunchmall.product.mapper.ProductMapper;
import com.zrrd.yunchmall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author JGX
 * @since 2024-01-15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private IProductAttributeValueService attributeValueService;

    @Autowired
    private IMemberPriceService memberPriceService;

    @Autowired
    private IProductLadderService productLadderService;

    @Autowired
    private IProductFullReductionService productFullReductionService;

    @Autowired
    private ISkuStockService skuStockService;

    @Autowired
    private ContentService contentService;

    /**
     * 重写Mybatis中的save方法
     */

    @Override
    @Transactional
    @SuppressWarnings("all")
    public boolean save(Product product) {
//        1.保存商品信息
        super.save(product);
//        2.保存商品属性值信息
        List<ProductAttributeValue> productAttributeValueList = product.getProductAttributeValueList();
        productAttributeValueList.forEach(item -> {
            item.setProductId(product.getId());//设置商品Id
        });
//        将所有项批量添加
        attributeValueService.saveBatch(productAttributeValueList);
//        3.保存商品会员价格信息
        List<MemberPrice> memberPriceList = product.getMemberPriceList();
        memberPriceList.forEach(item -> {
            item.setProductId(product.getId());//设置商品Id
        });
        memberPriceService.saveBatch(memberPriceList);
//        4.保存商品折扣信息
        List<ProductLadder> productLadderList = product.getProductLadderList();
        productLadderList.forEach(item -> {
            item.setProductId(product.getId());//设置商品Id
        });
        productLadderService.saveBatch(productLadderList);
//        5.商品满减信息
        List<ProductFullReduction> productFullReductionList = product.getProductFullReductionList();
        productFullReductionList.forEach(item -> {
            item.setProductId(product.getId());//设置商品Id
        });
        productFullReductionService.saveBatch(productFullReductionList);
//        6.增加SKU库存信息
        List<SkuStock> skuStockList = product.getSkuStockList();
        String skuCodePrefix = System.currentTimeMillis() + "";
        for (int i = 0; i < skuStockList.size(); i++) {
            skuStockList.get(i).setProductId(product.getId());
            String x = i >= 10 ? ("" + i) : ("0" + i);
            skuStockList.get(i).setSkuCode(skuCodePrefix + x);
        }
        skuStockService.saveBatch(skuStockList);
//        7.保存商品信息操作日志
//        ProductOperateLog productOperateLog = new ProductOperateLog();


//        8.保存商品关联专题信息（专题商品关系）
        List<SubjectProductRelation> subjectProductRelationList = product.getSubjectProductRelationList();
        subjectProductRelationList.forEach(item -> {
            item.setProductId(product.getId());
        });
        contentService.createSPR(subjectProductRelationList);


//        9.保存商品关联优选信息（用户喜好商品关系）
        List<PrefrenceAreaProductRelation> prefrenceAreaProductRelationList = product.getPrefrenceAreaProductRelationList();
        prefrenceAreaProductRelationList.forEach(item -> {
            item.setProductId(product.getId());
        });
        contentService.createAPR(prefrenceAreaProductRelationList);

        return true;
    }

    @Override
    @Transactional
    public Product getById(Serializable id) {
//        调用父类方法 添加基本商品信息
        Product product = super.getById(id);
        QueryWrapper queryWrapper = new QueryWrapper();

//        添加该商品对应属性值信息
        queryWrapper.eq("product_id", id);
        List<ProductAttributeValue> productAttributeValueList = attributeValueService.list(queryWrapper);
        product.setProductAttributeValueList(productAttributeValueList);
//        3.添加商品会员价格信息
        queryWrapper.clear();
        queryWrapper.eq("product_id", id);
        List<MemberPrice> memberPriceList = memberPriceService.list(queryWrapper);
        product.setMemberPriceList(memberPriceList);
//        4.添加商品折扣信息
        queryWrapper.clear();
        queryWrapper.eq("product_id", id);
        List<ProductLadder> productLadderList = productLadderService.list(queryWrapper);
        product.setProductLadderList(productLadderList);
//        5.商品满减信息
        queryWrapper.clear();
        queryWrapper.eq("product_id", id);
        List<ProductFullReduction> productFullReductionList = productFullReductionService.list(queryWrapper);
        product.setProductFullReductionList(productFullReductionList);
//        6.增加SKU库存信息
        queryWrapper.clear();
        queryWrapper.eq("product_id", id);
        List<SkuStock> skuStockList = skuStockService.list(queryWrapper);
        product.setSkuStockList(skuStockList);
//        7.添加商品关联专题信息（专题商品关系）
        List<SubjectProductRelation> subjectProductRelationList = (List) contentService.listSPR((Long) id).getData();
        product.setSubjectProductRelationList(subjectProductRelationList);
//        8.添加商品关联优选信息（用户喜好商品关系）
        List<PrefrenceAreaProductRelation> prefrenceAreaProductRelationList = (List) contentService.listAPR((Long) id).getData();
        product.setPrefrenceAreaProductRelationList(prefrenceAreaProductRelationList);

        return product;
    }


    @Override
    @Transactional
    public boolean updateById(Product product) {
        super.updateById(product);

//        建立查询条件
        long id = product.getId();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", id);

        attributeValueService.remove(queryWrapper);
        List<ProductAttributeValue> productAttributeValueList = product.getProductAttributeValueList();
        productAttributeValueList.forEach(item -> {
            item.setProductId(product.getId());//设置商品Id
        });
//        将所有项批量添加
        attributeValueService.saveBatch(productAttributeValueList);

//        3.保存商品会员价格信息
        memberPriceService.remove(queryWrapper);
        List<MemberPrice> memberPriceList = product.getMemberPriceList();
        memberPriceList.forEach(item -> {
            item.setProductId(product.getId());//设置商品Id
        });
        memberPriceService.saveBatch(memberPriceList);

//        4.保存商品折扣信息
        productLadderService.remove(queryWrapper);
        List<ProductLadder> productLadderList = product.getProductLadderList();
        productLadderList.forEach(item -> {
            item.setProductId(product.getId());//设置商品Id
        });
        productLadderService.saveBatch(productLadderList);

//        5.商品满减信息
        productFullReductionService.remove(queryWrapper);
        List<ProductFullReduction> productFullReductionList = product.getProductFullReductionList();
        productFullReductionList.forEach(item -> {
            item.setProductId(product.getId());//设置商品Id
        });
        productFullReductionService.saveBatch(productFullReductionList);

//        6.增加SKU库存信息
        skuStockService.remove(queryWrapper);
        List<SkuStock> skuStockList = product.getSkuStockList();
        String skuCodePrefix = System.currentTimeMillis() + "";
        for (int i = 0; i < skuStockList.size(); i++) {
            skuStockList.get(i).setProductId(product.getId());
            String x = i >= 10 ? ("" + i) : ("0" + i);
            skuStockList.get(i).setSkuCode(skuCodePrefix + x);
        }
        skuStockService.saveBatch(skuStockList);

//        8.保存商品关联专题信息（专题商品关系）
        contentService.deleteSPR(id);
        List<SubjectProductRelation> subjectProductRelationList = product.getSubjectProductRelationList();
        subjectProductRelationList.forEach(item -> {
            item.setProductId(product.getId());
        });
        contentService.createSPR(subjectProductRelationList);
//        9.保存商品关联优选信息（用户喜好商品关系）

        contentService.deleteAPR(id);
        List<PrefrenceAreaProductRelation> prefrenceAreaProductRelationList = product.getPrefrenceAreaProductRelationList();
        prefrenceAreaProductRelationList.forEach(item -> {
            item.setProductId(product.getId());
        });
        contentService.createAPR(prefrenceAreaProductRelationList);

        return true;
    }

    @Override
    @Transactional
    public void freeStock(List<Map<String, Long>> params) {
//        遍历params
//        更新商品库存
//        更新sku库存
        params.forEach(param -> {

            Long productId = param.get("productId");
            Long skuId = param.get("skuId");
            Long quantity = param.get("quantity");

            UpdateWrapper updateWrapper = new UpdateWrapper();
            QueryWrapper queryWrapper = new QueryWrapper();

            queryWrapper.select("stock");
            queryWrapper.eq("id", productId);
            int stock = super.getOne(queryWrapper).getStock();
            updateWrapper.set("stock", stock + quantity);
            updateWrapper.eq("id", productId);
//            更新商品表的库存
            super.update(updateWrapper);

            updateWrapper.clear();
            queryWrapper.clear();
            queryWrapper.select("stock");
            queryWrapper.eq("id", skuId);
            stock = skuStockService.getOne(queryWrapper).getStock();
            updateWrapper.set("stock", stock + quantity);
            updateWrapper.eq("id", skuId);
//            更新sku表库存
            skuStockService.update(updateWrapper);
        });
    }
}
