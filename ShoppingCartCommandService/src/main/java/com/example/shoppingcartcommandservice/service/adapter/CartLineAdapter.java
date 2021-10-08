package com.example.shoppingcartcommandservice.service.adapter;

import com.example.shoppingcartcommandservice.domain.CartLine;
import com.example.shoppingcartcommandservice.service.dto.CartLineDTO;
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
        return new CartLine(cartLine.getQuantity(),productAdapter.fromDTO(cartLine.getProduct()));
    }
    public CartLineDTO toDTO(CartLine cartLine){
        return new CartLineDTO(cartLine.getQuantity(),productAdapter.toDTO(cartLine.getProduct()));
    }
}
