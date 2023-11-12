package com.apsolutions;

import com.apsolutions.security.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApSolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApSolutionsApplication.class, args);
        // createPasswordEncoder();
    }

    public static void createPasswordEncoder() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        System.out.println(applicationConfig.passwordEncoder().encode("admin123"));
    }
}
