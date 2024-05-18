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

        String a="http://127.0.0.1:9000/test/300176_d400dc09d446578de0039e4c6b2754c6.jpg";
        extractObjectName(a);
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
