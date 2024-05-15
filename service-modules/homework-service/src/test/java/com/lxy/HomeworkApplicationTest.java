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

    @Test
    void demo() throws MinioException, IOException, NoSuchAlgorithmException, InvalidKeyException {
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
        String tempfiles = minioService.getObjectUrl("lxy", "712.jpg");
        System.out.println(tempfiles);



//        String objectUrl = minioService.getObjectUrl("lxy", "712.jpg");
//        System.out.println(objectUrl);


//        minioClient.get
//        minioAsyncClient.

    }


}
