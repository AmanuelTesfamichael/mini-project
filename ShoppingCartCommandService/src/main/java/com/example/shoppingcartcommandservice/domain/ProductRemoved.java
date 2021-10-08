package com.example.shoppingcartcommandservice.domain;

public class ProductRemoved extends CartEvent{
    private String productNumber;

    public ProductRemoved() {
        super("Product Removed",null);
    }

    public ProductRemoved(String productNumber,String cartNumber) {
        super("Product Removed",cartNumber);
        this.productNumber=productNumber;
    }

    public String getProductNumber() {
        return productNumber;
    }
}
