package com.zrrd.yunchmall.util;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.StatObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//提供视频或图片资源访问的controller
@RestController
public class ResourceAccessController {
    @Autowired
    private MinioProperties minioProperties;
    @Autowired
    private MinioClient minioClient;

    // /resource/20231112/de880074-0222-4311-acc2-e38b3b0480d2.mp4
    @GetMapping("/resource/{date}/{resourceName}")
    public void access(@PathVariable("date") String date,
                       @PathVariable("resourceName") String resourceName,
                       HttpServletResponse response) throws ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String filePath = date + "/" + resourceName;//完整的资源路径
        ObjectStat objectStat = minioClient.statObject(
                StatObjectArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .object(filePath)
                        .build()
        );
        if (objectStat==null || objectStat.length()==0) {
            response.setStatus(404);//表示资源未找到
            return;
        }
        response.setContentType(objectStat.contentType());//设置访问文件的格式
        //获取访问资源的输入流
        InputStream input = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .object(filePath)
                        .build()
        );
        byte[] buffer = new byte[12040];//定义一个10kb的缓冲区
        try(OutputStream output = response.getOutputStream()) {
            int read = 0;
            /*
            每次从input输入流中读取最多10kb的数据，放入buffer缓冲区；
            read中保存了每次读取到的实际的字节数
            然后再写入响应的输出流里，返回给客户端
             */
            while((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
                output.flush();
            }
        }
    }
}
