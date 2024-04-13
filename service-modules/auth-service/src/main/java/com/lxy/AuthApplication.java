package com.lxy;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.stereotype.Indexed;

@Indexed
@EnableDiscoveryClient
@EnableFeignClients
//@CrossOrigin
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})


public class AuthApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthApplication.class, args);


    }
}