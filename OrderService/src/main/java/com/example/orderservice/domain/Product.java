package com.example.orderservice.domain;

public class Product {
    private String productNumber;
    private String productName;

    public Product() {
    }

    public Product(String productNumber, String productName) {
        this.productNumber = productNumber;
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }
}
