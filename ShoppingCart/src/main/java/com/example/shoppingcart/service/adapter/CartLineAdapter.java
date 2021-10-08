package com.example.shoppingcart.service.adapter;

import com.example.shoppingcart.domain.CartLine;
import com.example.shoppingcart.service.dto.CartLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartLineAdapter {
    private ProductAdapter productAdapter;

    @Autowired
    public CartLineAdapter(ProductAdapter productAdapter) {
        this.productAdapter = productAdapter;
    }

    public CartLine fromDTO(CartLineDTO cartLine){
        return new CartLine(productAdapter.fromDTO(cartLine.getProduct()),cartLine.getQuantity());
    }
    public CartLineDTO toDTO(CartLine cartLine){
        return new CartLineDTO(cartLine.getQuantity(),productAdapter.toDTO(cartLine.getProduct()));
    }
}
