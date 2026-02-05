package com.akshansh.jdbc_rest_api;

import com.akshansh.jdbc_rest_api.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JdbcRestApiApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JdbcRestApiApplication.class, args);
    }
}
