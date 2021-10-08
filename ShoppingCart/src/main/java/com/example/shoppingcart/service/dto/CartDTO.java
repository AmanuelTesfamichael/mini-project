package com.example.shoppingcart.service.dto;

import java.util.List;

public class CartDTO {
    private List<CartLineDTO> cartLines;
    private String cartNumber;

    public CartDTO(List<CartLineDTO> cartLines,String cartNumber) {
        this.cartLines = cartLines;
        this.cartNumber = cartNumber;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public List<CartLineDTO> getCartLines() {
        return cartLines;
    }
}
