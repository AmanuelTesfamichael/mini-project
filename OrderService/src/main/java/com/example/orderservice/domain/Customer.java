package com.example.orderservice.domain;

public class Customer {
    private String customerNumber;

    public Customer(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
