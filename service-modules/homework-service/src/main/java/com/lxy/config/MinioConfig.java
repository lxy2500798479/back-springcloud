package com.lxy.config;


import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import lombok.Data;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                // 如果需要设置其他属性，比如 region、httpClient 等，可以在这里继续链式调用
                .build();
    }

    @Bean
    public MinioAsyncClient minioAsyncClient(){
        return MinioAsyncClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                // 如果需要设置其他属性，比如 region、httpClient 等，可以在这里继续链式调用
                .build();
    }
}

