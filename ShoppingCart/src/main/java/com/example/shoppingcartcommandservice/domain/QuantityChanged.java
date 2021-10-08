package com.example.shoppingcartcommandservice.domain;

public class QuantityChanged extends com.example.shoppingcartcommandservice.domain.CartEvent {


    private String productNumber;
    private int quantity;

    public QuantityChanged() {
        super("Quantity Changed",null);
    }

    public QuantityChanged(String productNumber, int quantity,String cartNumber) {
        super("Quantity Changed",cartNumber);
        this.productNumber=productNumber;
        this.quantity=quantity;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public int getQuantity() {
        return quantity;
    }
}
