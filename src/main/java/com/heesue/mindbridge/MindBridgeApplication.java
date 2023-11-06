package com.heesue.mindbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing //Spring Data JPA의 감사(audit) 기능이 활성화
public class MindBridgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MindBridgeApplication.class, args);
    }
}