package com.thc.smspr2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Smspr2Application {
    public static void main(String[] args) {
        SpringApplication.run(Smspr2Application.class, args);
    }
}
