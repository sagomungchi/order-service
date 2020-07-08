package com.pcbang.order.mvp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MvpApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvpApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedOrigins("http://localhost:8080")
                        .allowedMethods(
                                HttpMethod.HEAD.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.GET.name(),
                                HttpMethod.DELETE.name(),
                                HttpMethod.PUT.name());
            }
        };
    }
}
