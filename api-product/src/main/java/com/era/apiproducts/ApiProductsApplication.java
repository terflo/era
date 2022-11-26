package com.era.apiproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApiProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiProductsApplication.class, args);
    }

}
