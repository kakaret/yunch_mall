package com.zrrd.yunchmall;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zrrd.yunchmall.entity.TProduct;
import com.zrrd.yunchmall.repository.TProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest2 {
    @Autowired
    TProductRepository repository;

    @Test
    public void t1() {
        TProduct product = new TProduct(null, "黑莓手机", 2999.0, 1000);
        product = repository.save(product);
        System.out.println(product);
        System.out.println("OK");
    }

    @Test
    public void t2() {
        TProduct product = repository.findById(1).get();
        System.out.println(product);
    }

    @Test
    public void t5() {
        repository.deleteAll();
    }

    @Test
    public void t3() {
        List<TProduct> list = new ArrayList<>();
        for (int i = 11; i < 100; i++) {
            list.add(new TProduct(i, "手机型号-" + i, 3999.0, 1000));
        }
        repository.saveAll(list);
        System.out.println("OK");
    }

    //    查询全部记录
    @Test
    public void t4() {
        Iterable<TProduct> list = repository.findAll();
        for (TProduct product : list)
            System.out.println(product);
    }


    @Test
    public void t6() throws IOException {
//        页码从0开始
        int pageNum = 1, pageSize = 10;
        PageRequest request = PageRequest.of(pageNum - 1, pageSize);
        Page<TProduct> page = repository.findAll(request);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(page));
    }

    @Test
    public void t7() {
        Iterable list = repository.findAll(Sort.by("pid", "pprice"));
//        Iterable list = repository.findAll(Sort.by(Sort.Direction.DESC, "pprice"));
        list.forEach(System.out::println);
    }

    //    8 排序查找
    @Test
    public void t8() {
        List<TProduct> list = repository.findByPname("诺基亚");
        list.forEach(System.out::println);
    }

    @Test
    public void t9() {
        List<TProduct> list = repository.findByPprice(1000.0);
        list.forEach(System.out::println);
    }

    @Test
    public void t10() {
        List<TProduct> list = repository.findByPnameAndPprice("小米_测试库", 1000);
        list.forEach(System.out::println);
    }

    @Test
    public void t11() {
        List<TProduct> list = repository.findByPnameOrPprice("诺基亚", 1000);
        list.forEach(System.out::println);
    }

    @Test
    public void t12() {
        List<TProduct> list = repository.findByPpriceLessThan(1001); // <1001
        list.forEach(System.out::println);
    }

    @Test
    public void t13() {
        List<TProduct> list = repository.findByPpriceGreaterThan(2999); //>2999
        list.forEach(System.out::println);
    }

    @Test
    public void t14() {
        List<TProduct> list = repository.findByPpriceBetween(1000, 3000); // 1000<= price <=3000
        list.forEach(System.out::println);
    }

    @Test
    public void t15() {
        List<TProduct> list = repository.findByPnameLike("手机");
        list.forEach(System.out::println);
    }

    @Test
    public void t16() {
        List<TProduct> list = repository.findByPnameStartingWith("小米");
        list.forEach(System.out::println);
    }

    @Test
    public void t17() {
        List<TProduct> list = repository.findByPnameEndingWith("5");
        list.forEach(System.out::println);
    }

    @Test
    public void t18() {
        List<TProduct> list = repository.findByPnameContaining("OPPO");
        list.forEach(System.out::println);
    }

    @Test
    public void t19() {
        List<TProduct> list = repository.findByPnameContains("华为");
        list.forEach(System.out::println);
    }

    @Test
    public void t20() throws JsonProcessingException {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<TProduct> page = repository.findByPpriceBetween(1000, 5999, pageRequest);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(page));
//        page.forEach(System.out::println);
    }

    @Test
    public void t21() throws JsonProcessingException {
//        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<TProduct> page = repository.findByPpriceBetween(1000, 5999, Pageable.unpaged());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(page));
//        page.forEach(System.out::println);
    }

    @Test
    public void t22() {
//        Sort sort = Sort.unsorted(); //不使用排序规则
//        价格的逆序和库存的正序
        Sort sort = Sort.by(Sort.Direction.DESC, "pprice").and(Sort.by("stock").ascending());
        Sort sort2 = Sort.by("pprice").descending().and(Sort.by("stock").ascending());
        List<TProduct> list = repository.findByPnameLike("手机");
        list.forEach(System.out::println);
    }

}