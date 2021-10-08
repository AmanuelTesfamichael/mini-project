package com.example.shoppingcartcommandservice.domain;

public class Checkout extends CartEvent{
    private final String customerNumber;

    public Checkout(String cartNumber, String customerNumber) {
        super("Checkout",cartNumber);
        this.customerNumber=customerNumber;
    }

    @Override
    public String getCartNumber() {
        return super.getCartNumber();
    }
}
