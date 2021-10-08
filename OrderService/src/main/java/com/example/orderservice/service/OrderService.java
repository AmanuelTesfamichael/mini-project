package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
    public void addOrder(Order order){
        repository.save(order);
    }
}
