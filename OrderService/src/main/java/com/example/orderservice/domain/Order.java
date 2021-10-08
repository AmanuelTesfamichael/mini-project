package com.example.orderservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Order {
    @Id
    private String orderNumber;
    private List<OrderLine> orderLines=new ArrayList<>();
    private Customer customer;

    public Order() {
    }

    public Order(List<OrderLine> orders,Customer customer) {
        this.orderNumber = orderNumber;
        this.orderLines = orders;
        this.customer = customer;
    }
    public void add(OrderLine orderLine){
        orderLines.add(orderLine);
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
