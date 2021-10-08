package com.example.productservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    @Id
    private String productNumber;
    private String productName;
    private double productPrice;
    private String productDescription;
    private int available;

    public Product(String productNumber, String productName, double productPrice, String productDescription, int available) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.available = available;
    }
    //take amount number of product from the stock
    public void take(int amount){
        if(amount>this.available)throw new IllegalStateException("can't take more than available");
        else{
            this.available-=amount;
        }
    }
    //add amount number of product from the stock
    public void add(int amount){
        this.available+=amount;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getAvailable() {
        return available;
    }
}
