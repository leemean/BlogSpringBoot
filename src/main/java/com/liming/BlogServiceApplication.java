package com.liming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogServiceApplication {
    public static void main(String[] args){
        SpringApplication app = new SpringApplication(BlogServiceApplication.class);
        app.run(args);
    }
}
