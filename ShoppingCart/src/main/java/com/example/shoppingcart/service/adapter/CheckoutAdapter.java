package com.example.shoppingcart.service.adapter;

import com.example.shoppingcart.service.dto.CheckoutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutAdapter {
    private CartAdapter cartAdapter;

    @Autowired
    public CheckoutAdapter(CartAdapter cartAdapter) {
        this.cartAdapter = cartAdapter;
    }

    public CheckoutDTO toDTO(Checkout checkout){
        return new CheckoutDTO(checkout.getCustomerNumber(),cartAdapter.toDTO(checkout.getCart()));
    }
}
