package com.example.demo;

import com.example.demo.websocketServer.STOMPWebSocket.utils.UserInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public UserInterceptor createUserInterceptor() {
        return new UserInterceptor();
    }
}
