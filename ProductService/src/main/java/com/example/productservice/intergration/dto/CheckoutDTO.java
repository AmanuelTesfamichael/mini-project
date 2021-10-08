package com.example.productservice.intergration.dto;
public class CheckoutDTO {
    private String customerNumber;
    private CartDTO cart;

    public CheckoutDTO() {
    }

    public CheckoutDTO(String customerNumber, CartDTO cart) {
        this.customerNumber = customerNumber;
        this.cart = cart;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public CartDTO getCart() {
        return cart;
    }
}

