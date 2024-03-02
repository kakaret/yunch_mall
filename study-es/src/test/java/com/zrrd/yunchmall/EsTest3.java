package com.zrrd.yunchmall;

import com.zrrd.yunchmall.entity.TProduct;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.ParsedMin;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest3 {
    //    练习使用ElasticsearchRestTemplate
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    //    1 精确查询：不分析查询条件 只有当词条和查询字符串完全匹配，比如：数字 日期 布尔值 或者未经分析的字符串
    @Test
    public void t1() {
//        定义查询条件
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("stock", 99);
//        根据查询条件创建一个查询器
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(termQueryBuilder).build();
//        调用模板类中的search方法进行检索
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
//        获取满足查询条件的结果个数
        System.out.println("Totals: " + searchHits.getTotalHits());
//        循环遍历输出查询到的结果
        searchHits.getSearchHits().forEach(item -> {
            System.out.println(item.getContent());
        });

    }

    //2 全文（match）查询：会对查询条件进行分析（分词），然后查询，求并集
    @Test
    public void t2() {
        // 匹配全部结果
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        // 根据查询条件创建一个查询器
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQueryBuilder).build();
        // 调用模板类中的search方法进行检索
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
        // 获取满足查询条件的结果个数
        System.out.println("Total:" + searchHits.getTotalHits());
        // 循环遍历输出查到的结果
        searchHits.getSearchHits().forEach(item -> {
            System.out.println(item.getContent());
        });
    }

    @Test
    public void t3() {
        // 匹配全部结果(分词)
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("pname", "诺基");
        // 根据查询条件创建一个查询器
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(matchQueryBuilder).build();
        // 调用模板类中的search方法进行检索
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
        // 获取满足查询条件的结果个数
        System.out.println("Total:" + searchHits.getTotalHits());
        // 循环遍历输出查到的结果
        searchHits.getSearchHits().forEach(item -> {
            System.out.println(item.getContent());
        });
    }

    @Test
    public void t4() {
//        短语精确查询
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("pname", "华为");
        // 根据查询条件创建一个查询器
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQueryBuilder).build();
        // 调用模板类中的search方法进行检索
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
        // 获取满足查询条件的结果个数
        System.out.println("Total:" + searchHits.getTotalHits());
        // 循环遍历输出查到的结果
        searchHits.getSearchHits().forEach(item -> {
            System.out.println(item.getContent());
        });
    }

    @Test
    public void t5() {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("华为", "pname", "title", "keyword", "desc");
//        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("华为", "pname");
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(multiMatchQueryBuilder).build();
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
        System.out.println("Total: " + searchHits.getTotalHits());
        searchHits.getSearchHits().forEach(item -> {
            System.out.println(item.getContent());
        });
    }

    //    分页查询
    @Test
    public void t6() {
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
//        针对属性进行排序
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("pid").order(SortOrder.ASC);
//        使用PageRequest进行分页设置
        PageRequest pageRequest = PageRequest.of(0, 10);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQueryBuilder)
                .withSort(fieldSortBuilder)
                .withPageable(pageRequest)
                .build();
//        调用模板类中的search方法进行检索
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
        System.out.println("总共条数：" + searchHits.getTotalHits());
        System.out.println("当前页号：" + pageRequest.getPageNumber() + 1);
        System.out.println("每页条数：" + pageRequest.getPageSize());
        long pages = searchHits.getTotalHits() % pageRequest.getPageSize() == 0 ?
                searchHits.getTotalHits() / pageRequest.getPageSize() :
                searchHits.getTotalHits() / pageRequest.getPageSize() + 1;
        System.out.println("一共有多少页：" + pages);
        System.out.println("当前页有多少条：" + searchHits.getSearchHits().size());
        searchHits.getSearchHits().forEach(item -> {
            System.out.println(item.getContent());
        });
    }

    //    范围查询
    @Test
    public void t7() {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("pprice").from(0).to(3000);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(rangeQueryBuilder)
                .build();
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
        System.out.println("Total: " + searchHits.getTotalHits());
        searchHits.getSearchHits().forEach(item -> {
            System.out.println(item.getContent());
        });
    }

    @Test
    public void t8() {
//        查询带有固定词语的商品信息 例如 华为手机
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery("华为手机");
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQueryBuilder)
                .build();
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
        System.out.println("Total: " + searchHits.getTotalHits());
        searchHits.getSearchHits().forEach(item -> {
            System.out.println(item.getContent());
        });
    }

    //    案例：查询结果的高亮性设置
    @Test
    public void t9() {
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("pname").preTags("<font color='red'>").postTags("</font>");
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQueryBuilder)
                .withHighlightBuilder(highlightBuilder)
                .build();
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, TProduct.class);
        System.out.println("Total: " + searchHits.getTotalHits());
        searchHits.getSearchHits().forEach(item -> {
            TProduct product = item.getContent();
//            获取所有的高亮结果
            Map<String, List<String>> highlightFields = item.getHighlightFields();
//            遍历所有高亮结果的field属性
            for (String field : highlightFields.keySet()) {
                Class<?> aClass = product.getClass();
                try {
                    Field declareField = aClass.getDeclaredField(field);
                    declareField.setAccessible(true);
                    declareField.set(product, highlightFields.get(field).get(0));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(product);
        });
    }

    @Test
    public void t10() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        分别聚合计算价格中的最大值和最小值
        queryBuilder.addAggregation(AggregationBuilders.max("maxPrice").field("pprice"));
        queryBuilder.addAggregation(AggregationBuilders.min("minPrice").field("pprice"));
        queryBuilder.withSourceFilter(new FetchSourceFilterBuilder().build());
//        执行查询
        SearchHits<TProduct> searchHits = elasticsearchRestTemplate.search(queryBuilder.build(), TProduct.class, IndexCoordinates.of("t_product"));
        Aggregations aggregations = searchHits.getAggregations();
        if(aggregations != null) {
//            打印聚合结果
            ParsedMax max = aggregations.get("maxPrice");
            System.out.println("最贵的手机价格是：" + max.getValue());
            ParsedMin min = aggregations.get("minPrice");
            System.out.println("最便宜的手机价格是：" + min.getValue());
        }

    }

}
