package com.clipboard.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ClipboardApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClipboardApiApplication.class, args);
    }

}
