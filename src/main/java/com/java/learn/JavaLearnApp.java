package com.java.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JavaLearnApp {
    public static void main(String[] args) {
        SpringApplication.run(JavaLearnApp.class,args);
    }
}
