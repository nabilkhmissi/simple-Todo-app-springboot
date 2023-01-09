package com.example.simplecontactapp;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@AllArgsConstructor
public class SimpleContactAppApplication{


    public static void main(String[] args) {
        SpringApplication.run(SimpleContactAppApplication.class, args);
    }


}

