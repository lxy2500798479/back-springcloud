package com.lxy;


import com.kangaroohy.minio.entity.MultiPartUploadInfo;
import com.kangaroohy.minio.service.MinioService;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SpringBootTest
public class HomeworkApplicationTest {

    @Autowired
    MinioClient minioClient;

    @Autowired
    MinioAsyncClient minioAsyncClient;

    @Autowired
    MinioService minioService;

    private void extractObjectName(String fileUrl) {
        // 解析URL，提取对象名 (根据你URL的格式做相应解析)
        String[] parts = fileUrl.split("/");
        for (int i = 0; i < parts.length; i++){
            System.out.println(parts[i]);
        }
//        return parts[4] + "/" + parts[5]; // 假设第5和第6部分组成对象名
    }

    @Test
    void demo() throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {
        InputStream object = minioService.getObject("test", "1-%25E6%25AD%25A4......%25E6%25AD%25A4%25E8%25A7%2586%25E9%25A2%2591%25E6%2597%25A0%25E4%25BA%25BA%25E8%2583%25BD%25E7%2599%25BD%25E5%25AB%2596%25EF%25BC%2581-1080P%2520%25E9%25AB%2598%25E6%25B8%2585-AVC_7a357e9ecfb0c774d28cf3dc2638f451.mp4");



//        String a="http://127.0.0.1:9000/test/300176_d400dc09d446578de0039e4c6b2754c6.jpg";
//        extractObjectName(a);
//        minioClient.listBuckets().forEach(bucket ->{
//            System.out.println(bucket.name());
//            System.out.println(bucket.creationDate());
//        });
//        System.out.println("===========");
//        CompletableFuture<List<Bucket>> listCompletableFuture = minioAsyncClient.listBuckets();
//        listCompletableFuture.thenAccept(buckets -> {
//            buckets.forEach(bucket -> {
//                System.out.println(bucket.name());
//                System.out.println(bucket.creationDate());
//            });
//        });
//        minioClient.composeObject()
//        MultiPartUploadInfo tempfiles = minioService.initMultiPartUploadId("test", "test.txt", 5, "text/plain");
//        System.out.println(tempfiles.getUploadId());
//        tempfiles.getUploadUrls().forEach(System.out::println);
//
//        System.out.println(tempfiles);

//        System.out.println(minioService.getPresignedPostFormData("test", "712.jpg"));
//        List<Bucket> buckets = minioClient.listBuckets();
//        buckets.forEach(bucket -> {
//            System.out.println(bucket.name());
//            System.out.println(bucket.creationDate());
//        });
//
//        String tempfiles = minioService.getObjectUrl("lxy", "712.jpg");
//        System.out.println(tempfiles);



//        minioService.getObject()

//        minioClient.statObject();


//        String objectUrl = minioService.getObjectUrl("lxy", "712.jpg");
//        System.out.println(objectUrl);


//        minioClient.get
//        minioAsyncClient.

    }


}
