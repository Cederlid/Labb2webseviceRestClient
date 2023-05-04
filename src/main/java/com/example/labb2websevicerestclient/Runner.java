package com.example.labb2websevicerestclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Runner implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        productRepository.menu();
    }
}
