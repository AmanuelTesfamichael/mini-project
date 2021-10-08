package com.example.restclient.cartcommand;

public class ProductDTO {
    private String productNumber;
    private String productName;
    private String productDescription;
    private double productPrice;

    public ProductDTO() {
    }

    public ProductDTO(String productNumber, String productName, String productDescription, double price) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = price;
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

    public double getProductPrice() {
        return productPrice;
    }
}
