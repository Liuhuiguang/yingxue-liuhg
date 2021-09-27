package com.liuhg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@tk.mybatis.spring.annotation.MapperScan("com.liuhg.dao")
@MapperScan("com.liuhg.dao")
@SpringBootApplication
public class YingxueLiuhgApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxueLiuhgApplication.class, args);
    }

}

