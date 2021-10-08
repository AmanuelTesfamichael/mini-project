package com.example.shoppingcart.service.adapter;

import com.example.shoppingcart.domain.Cart;
import com.example.shoppingcart.service.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartAdapter {
    private CartLineAdapter adapter;

    @Autowired
    public CartAdapter(CartLineAdapter adapter) {
        this.adapter = adapter;
    }

    public Cart fromDTO(CartDTO cartDTO){
        return new Cart(cartDTO.getCartNumber(),cartDTO.getCartLines().stream().map(adapter::fromDTO).collect(Collectors.toList()));
    }
    public CartDTO toDTO(Cart cart){
        return new CartDTO(cart.getCartLines().stream().map(adapter::toDTO).collect(Collectors.toList()), cart.getCartNumber());
    }
}
