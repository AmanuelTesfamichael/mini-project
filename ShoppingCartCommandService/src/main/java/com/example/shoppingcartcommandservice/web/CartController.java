package com.example.shoppingcartcommandservice.web;

import com.example.shoppingcartcommandservice.service.CartService;
import com.example.shoppingcartcommandservice.service.OutOfStockException;
import com.example.shoppingcartcommandservice.service.dto.CartLineDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartcommand")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartNumber}")
    public ResponseEntity<?> addToCart(@PathVariable String cartNumber,@RequestBody CartLineDTO cartLine){
        cartService.addToCart(cartNumber,cartLine);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{cartNumber}/{productNumber}")
    public ResponseEntity<?> removeProduct(@PathVariable String cartNumber, @PathVariable String productNumber){
        cartService.removeProduct(cartNumber,productNumber);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{cartNumber}/{productNumber}/{newQuantity}")
    public ResponseEntity<?> updateQuantity(@PathVariable String productNumber,@PathVariable String cartNumber, @PathVariable int newQuantity){
        cartService.changeQuantity(cartNumber,productNumber,newQuantity);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{cartNumber}/checkout/{customerNumber}")
    public ResponseEntity<?> checkout(@PathVariable String cartNumber,@PathVariable String customerNumber){
        cartService.checkOut(cartNumber,customerNumber);
        return ResponseEntity.ok().build();
    }
    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<?> handle(){
        return ResponseEntity.badRequest().body("Out of stock");
    }
}
