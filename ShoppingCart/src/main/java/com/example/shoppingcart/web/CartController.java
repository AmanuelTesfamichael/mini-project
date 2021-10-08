package com.example.shoppingcart.web;

import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;

@RequestMapping("/cartquery")
@RestController
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/{cartNumber}")
    public ResponseEntity<CartDTO> getCart(@PathVariable String cartNumber){
        return cartService.getCart(cartNumber).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
