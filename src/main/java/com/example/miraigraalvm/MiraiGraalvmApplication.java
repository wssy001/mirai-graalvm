package com.example.miraigraalvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class MiraiGraalvmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiraiGraalvmApplication.class, args);
    }

}
