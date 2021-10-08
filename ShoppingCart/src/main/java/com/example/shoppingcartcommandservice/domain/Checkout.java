package com.example.shoppingcartcommandservice.domain;

public class Checkout extends CartEvent{
    private String customerNumber;

    public Checkout(){
        super("Checkout",null);
    }
    public Checkout(String cartNumber, String customerNumber) {
        super("Checkout",cartNumber);
        this.customerNumber=customerNumber;
    }

    @Override
    public String getCartNumber() {
        return super.getCartNumber();
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
}
