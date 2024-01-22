package com.zrrd.yunchmall.util;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//提供文件上传服务的控制器
@RestController
//处理跨域服务文件上传
@CrossOrigin("*")
public class UploadController {
    @Autowired
    private MinioProperties minioProperties;
    @Autowired
    private MinioClient minioClient;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Value("${server.address}")
    private String address; //当前服务得IP地址

    @Value("${server.port}")
    private String port; //当前服务得端口号

    private String randomFilename(String originalFilename) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateFormat.format(new Date()));//20231111
        stringBuilder.append("/");
        stringBuilder.append(UUID.randomUUID().toString());//8ac14e12-079e-d7e3-8a80-73b4fe3d6473
        stringBuilder.append(originalFilename.substring(originalFilename.lastIndexOf(".")));//.mp4
        return stringBuilder.toString();
    }

    @PostMapping("/minio/upload")
    public ResponseResult upload(MultipartFile file) throws ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 火影忍者——第二集.mp4 上传文件的原始文件名
        String originalFilename = file.getOriginalFilename();
        // 但是上传到服务器上的文件名需要是唯一的，如何保证唯一性？ /日期/UUID.后缀
        // 20231111/8ac14e12-079e-d7e3-8a80-73b4fe3d6473.webp
        String filename = randomFilename(originalFilename);//得到一个随机的资源名称
        boolean found = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .build()
        );
        if (!found) {//如果指定的bucket不存在，则抛出异常
            throw new RuntimeException("Minio buket(" + minioProperties.getBucketName() + ") is not Found.");
        }
//        @Cleanup
        InputStream in = file.getInputStream();//获取上传文件的输入流
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())//指定存储文件的桶
                .object(filename)//指定上传资源的名称
                .contentType(file.getContentType())//保存的资源是什么类型（格式）
                .stream(in, file.getSize(), -1)//指定输入流，文件大小，分片个数
                .build();
        minioClient.putObject(putObjectArgs);//保存文件
        in.close();//关闭输入流
        System.out.println("上传成功");
        String host = "http://" + address + ":" + port;
        Map data = new HashMap();
        data.put("url", host + "/resource/" + filename);
        return new ResponseResult<>(200, "上传成功", data);
//        return host + "/resource/" + filename;//返回最终的资源路径
    }

}
