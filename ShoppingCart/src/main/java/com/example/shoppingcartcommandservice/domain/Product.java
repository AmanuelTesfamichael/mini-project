package com.example.shoppingcartcommandservice.domain;

import org.springframework.data.annotation.Id;


public class Product {
    private  double price;
    @Id
    private String productNumber;
    private String productName;
    private double productPrice;
    private String productDescription;

    public Product() {
    }

    public Product(String productNumber, String productName, double productPrice, String productDescription, double price) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.price=price;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public double getPrice() {
        return price;
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
}
