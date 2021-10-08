package com.example.shoppingcartcommandservice.domain;

public class ProductAdded extends CartEvent{
    private CartLine cartLine;

    public ProductAdded() {
        super("Product Added",null);
    }

    public ProductAdded(CartLine cartLine,String cartNumber) {
        super("Product Added",cartNumber);
        this.cartLine = cartLine;
    }

    public CartLine getCartLine() {
        return cartLine;
    }
}
