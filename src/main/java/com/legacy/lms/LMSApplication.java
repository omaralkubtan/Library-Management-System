package com.legacy.lms;

import com.legacy.lms.config.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
@EnableScheduling
@EnableFeignClients
public class LMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(LMSApplication.class, args);
    }
}
