package com.example.orderservice.repository;

import com.example.orderservice.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,String> {
}
