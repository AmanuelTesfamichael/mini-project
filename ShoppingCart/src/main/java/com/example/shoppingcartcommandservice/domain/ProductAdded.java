package com.example.shoppingcartcommandservice.domain;

public class ProductAdded extends CartEvent{
    private CartLine cartLine;

    public ProductAdded() {
        super("com.example.shoppingcartcommandservice.domain.Product Added",null);
    }

    public ProductAdded(CartLine cartLine,String cartNumber) {
        super("com.example.shoppingcartcommandservice.domain.Product Added",cartNumber);
        this.cartLine = cartLine;
    }

    public CartLine getCartLine() {
        return cartLine;
    }
}
