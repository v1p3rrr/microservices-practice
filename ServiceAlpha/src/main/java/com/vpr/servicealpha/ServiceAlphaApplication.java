package com.vpr.servicealpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ServiceAlphaApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAlphaApplication.class, args);
    }

}
