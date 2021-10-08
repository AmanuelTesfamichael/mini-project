package com.example.shoppingcart.service.adapter;

import com.example.shoppingcart.domain.Cart;

public class Checkout {
    private String customerNumber;
    private Cart cart;

    public Checkout(String customerNumber, Cart cart) {
        this.customerNumber = customerNumber;
        this.cart = cart;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public Cart getCart() {
        return cart;
    }
}
