package com.yyh.selenium_medi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yyh.selenium_medi.dao")
public class SeleniumMediApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeleniumMediApplication.class, args);
    }

}
