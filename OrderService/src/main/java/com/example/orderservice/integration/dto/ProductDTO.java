package com.example.orderservice.integration.dto;

public class ProductDTO {
    private String productNumber;
    private String productName;
    private String productDescription;
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(String productNumber, String productName, String productDescription, double price) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getPrice() {
        return price;
    }
}
