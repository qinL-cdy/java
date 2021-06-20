package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class OrderService {

    public void order(String id) {
        System.out.println(id);
    }
}
