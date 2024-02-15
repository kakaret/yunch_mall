package com.zrrd.yunchmall;

import org.elasticsearch.action.admin.indices.create.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsTest1 {
    @Autowired
    private RestHighLevelClient esClient;

    @Test
    public void t1() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("index_shz");
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }
}
