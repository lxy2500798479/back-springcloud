package com.lxy;


import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class HomeworkApplicationTest {

    @Autowired
    MinioClient minioClient;

    @Test
    void demo() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.listBuckets().forEach(bucket ->{
            System.out.println(bucket.name());
            System.out.println(bucket.creationDate());
        });
//        minioClient.putObject(PutObjectArgs.builder()
//                .bucket()
//                .object()
//
//        )
    }


}
