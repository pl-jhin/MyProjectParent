package com.penglei.ssologin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.penglei.mapper","com.penglei.ssologin"})
public class SsoLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoLoginApplication.class, args);

    }
}
