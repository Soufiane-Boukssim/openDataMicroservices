package com.soufiane.dataset_theme_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.soufiane.dataset_theme_service",
        "com.soufiane.sharedlibrary"
})

public class DatasetThemeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasetThemeServiceApplication.class, args);
    }

}
