package com.example.shoppingcartcommandservice.domain;

public class ProductRemoved extends CartEvent{
    private String productNumber;

    public ProductRemoved() {
        super("com.example.shoppingcartcommandservice.domain.Product Removed",null);
    }

    public ProductRemoved(String productNumber,String cartNumber) {
        super("com.example.shoppingcartcommandservice.domain.Product Removed",cartNumber);
        this.productNumber=productNumber;
    }

    public String getProductNumber() {
        return productNumber;
    }
}
