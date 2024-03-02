package com.zrrd.yunchmall;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zrrd.yunchmall.entity.Student;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest1 {
    @Autowired
    private RestHighLevelClient esClient;


    //    学习使用ES的Java API来完成ES的访问
//    常用的http访问方式就是 GET(查询) PUT(创建) POST(更新) DELETE(删除)
//    1 创建一个ES索引
    @Test
    public void t1() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("index_shz");
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    //    2 查询索引有没有创建成功 (索引是否存在)
    @Test
    public void t2() throws IOException {
//        创建一个查询索引的请求
        GetIndexRequest request = new GetIndexRequest("index_shz");
//        判断索引是否存在
        boolean isExist = esClient.indices().exists(request, RequestOptions.DEFAULT);
//        输出结果
        System.out.println("索引index_shz是否存在：" + isExist);
    }

    //    3 删除索引
    @Test
    public void t3() throws IOException {
//        创建一个删除索引的请求对象
        DeleteIndexRequest request = new DeleteIndexRequest("index_test5");
//        通过调用ES客户端接口删除指定名称的索引
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);
//        输出结果
        System.out.println(response);
    }

    //    4 创建索引文件
    @Test
    public void t4() throws IOException {
//        创建一个学生
        Student student = new Student("豹子头林冲", 32);
        IndexRequest request = new IndexRequest("index_shz");
//        配置核心属性
        request.id("6"); //文档Id
        request.timeout("1s"); //请求超时时间
//        request.timeout(TimeValue.timeValueSeconds(1)); //同上
        ObjectMapper objectMapper = new ObjectMapper();
//        将student对象序列化成JSON字符串
        request.source(objectMapper.writeValueAsString(student), XContentType.JSON);
//        执行请求 保存返回结果
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    @Test
    public void t5() throws IOException {
//        使用Get请求
        GetRequest request = new GetRequest("index_shz", "6");
//        配置不显示索引上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
//        获取文档信息
        boolean exists = esClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

//    6 查询文档内容
    @Test
    public void t6() throws IOException {
//        创建查询请求
        GetRequest request = new GetRequest("index_shz", "6");
//        执行查询请求 保存响应结果
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        System.out.println("--------------------------");
        System.out.println(response);
     }

//     7 改变内容
    @Test
    public void t7() throws IOException {
        UpdateRequest request = new UpdateRequest("index_shz", "6");
        Student student = new Student("林教头", 28);
        ObjectMapper objectMapper = new ObjectMapper();
//        设置请求内容
        request.doc(objectMapper.writeValueAsString(student), XContentType.JSON);
//        执行更新方法
        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);
//        输出响应结果
        System.out.println(response.getResult());
    }

//    8 删除文档
    @Test
    public void t8() throws IOException {
//        创建一个删除文档的请求对象
        DeleteRequest request = new DeleteRequest("index_shz", "6");
//        执行删除请求 保存响应
        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);
//        输出响应结果
        System.out.println(response);
    }

//    9 批量插入文档
    @Test
    public void t9() throws IOException {
        ArrayList list = new ArrayList();
        list.add(new Student("林冲", 25));
        list.add(new Student("鲁智深", 35));
        list.add(new Student("武松", 30));
        list.add(new Student("刘松", 30));
        list.add(new Student("李逵", 32));
        list.add(new Student("宋江", 38));
        list.add(new Student("吴用", 35));
//        创建一个支持批量操作的请求对象
        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(new IndexRequest("index_shz")
                    .id(String.valueOf(i + 1))
                    .source(objectMapper.writeValueAsString(list.get(i)), XContentType.JSON)
            );
        }

        BulkResponse response = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response);
     }

//     10 根据条件检索文档内容
    @Test
    public void t10() throws IOException {
        SearchRequest searchRequest = new SearchRequest("index_shz");
//        设置检索条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("name", "宋江"));
        searchRequest.source(builder);
//        执行检索操作 保存结果
        SearchResponse response = esClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(response.getHits());
        System.out.println("-----------------------");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

}
