package com.zrrd.yunchmall.repository;

import com.zrrd.yunchmall.entity.TProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//自定义jpa接口 继承ElasticsearchRepository<实体类泛型, id属性泛型>
@Repository
@SuppressWarnings("all")
public interface TProductRepository extends ElasticsearchRepository<TProduct, Integer> {
//    根据商品名进行查找
    List<TProduct> findByPname(String pname);
//    根据商品价格进行查找
    List<TProduct> findByPprice(Double price);
//    根据商品名和商品价格进行检索
    List<TProduct> findByPnameAndPprice(String pname, double price);
    List<TProduct> findByPnameOrPprice(String pname, double price);

//    范围查询：查询价格在指定区间的商品
    List<TProduct> findByPpriceBetween(double p1, double p2);
    List<TProduct> findByPpriceLessThan(double p1); //findByPpriceBefore
    List<TProduct> findByPpriceGreaterThan(double p1); //findByPpriceAfter

//    实现分页的方法
    Page<TProduct> findByPpriceBetween(double p1, double p2, Pageable pageable);

    //    增加一个用于设置排序规则的参数
    List<TProduct> findByPnameLike(String keyword, Sort sort);

//    字符串模糊查找的方法
    List<TProduct> findByPnameLike(String keyword);
    List<TProduct> findByPnameNotLike(String keyword);
    List<TProduct> findByPnameStartingWith(String keyword);
    List<TProduct> findByPnameEndingWith(String keyword);
    List<TProduct> findByPnameContaining(String keyword);
    List<TProduct> findByPnameContains(String keyword);
//    增加排序的谓词
    List<TProduct> findByPnameContainingOrderByPpriceAsc(String keyword);
    List<TProduct> findByPnameContainingOrderByPpriceDesc(String keyword);

    List<TProduct> findByPnameOrderByPpriceAsc(String keyword);
}
