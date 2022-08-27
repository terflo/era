package com.era.apicourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.era.apicourse.model.repositories")
public class ApiCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCourseApplication.class, args);
    }

}
