package com.example.productservice.service.dto;

public class ProductDTO {

    private String productNumber;
    private String productName;
    private double productPrice;
    private String productDescription;
    private int available;

    public ProductDTO(String productNumber, String productName, double productPrice, String productDescription, int available) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.available = available;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getAvailable() {
        return available;
    }
}
