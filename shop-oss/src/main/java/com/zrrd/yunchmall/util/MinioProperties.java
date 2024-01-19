package com.zrrd.yunchmall.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//用来动态加载 yml 当中关于minion的属性
@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
