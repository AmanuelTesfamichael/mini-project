package com.example.shoppingcart.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RedisHash
public class Cart {
    @Id
    private String cartNumber;
    private List<CartLine> cartLines;

    public Cart() {
        cartLines=new ArrayList<>();
    }

    public Cart(String cartNumber, List<CartLine> cartLines) {
        this.cartNumber = cartNumber;
        this.cartLines = cartLines;
    }

    public String getCartNumber() {
        return cartNumber;
    }
    public Optional<CartLine> getByProductNumber(String productNumber){
        return cartLines.stream()
                .filter(cartLine -> cartLine.getProduct().getProductNumber().equals(productNumber))
                .findAny();
    }
    public void removeProduct(String productNumber){
        cartLines.removeIf(cartLine -> cartLine.getProduct().getProductNumber().equals(productNumber));
    }
    public void add(CartLine cartLine){
        cartLines.add(cartLine);
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }
}
