package com.google;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.google.mapper")
public class CodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class, args);
    }

}
