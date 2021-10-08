package com.example.shoppingcart.service;

import com.example.shoppingcart.domain.Cart;
import com.example.shoppingcart.integration.KafkaSender;
import com.example.shoppingcart.repository.CartDAO;
import com.example.shoppingcart.service.adapter.CartAdapter;
import com.example.shoppingcart.service.adapter.CartLineAdapter;
import com.example.shoppingcart.service.adapter.Checkout;
import com.example.shoppingcart.service.adapter.CheckoutAdapter;
import com.example.shoppingcart.service.dto.CartDTO;
import com.example.shoppingcart.service.dto.CartLineDTO;
import com.example.shoppingcart.service.dto.CheckoutDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private CartAdapter cartAdapter;
    private CartLineAdapter cartLineAdapter;
    private CartDAO cartDAO;
    private KafkaSender kafkaSender;
    private CheckoutAdapter checkoutAdapter;

    @Autowired
    public CartService(CartAdapter cartAdapter, CartLineAdapter cartLineAdapter, CartDAO cartDAO, KafkaSender kafkaSender, CheckoutAdapter checkoutAdapter) {
        this.cartAdapter = cartAdapter;
        this.cartLineAdapter = cartLineAdapter;
        this.cartDAO = cartDAO;
        this.kafkaSender = kafkaSender;
        this.checkoutAdapter = checkoutAdapter;
    }

    private void createCart(CartDTO cart){
        logger.info("creating a new cart");
        cartDAO.save(cartAdapter.fromDTO(cart));
    }
    public void addToCart(String cartNumber,CartLineDTO cartLine){
        logger.info("Adding new product to a cart");
        cartDAO.findById(cartNumber).ifPresentOrElse(cart -> cart.add(cartLineAdapter.fromDTO(cartLine)),()->createCart(new CartDTO(List.of(cartLine),cartNumber)));
    }
    public void changeQuantity(String cartNumber, String productNumber, int newQuantity){
        cartDAO.findById(cartNumber).ifPresent(cart -> {
            cart.getByProductNumber(productNumber).ifPresent(cartLine -> {
                cartLine.changeQuantity(newQuantity);
            });
        });
    }
    public void removeProduct(String cartNumber,String productNumber){
        cartDAO.findById(cartNumber).ifPresent(cart -> {
            cart.removeProduct(productNumber);
        });
    }
    public void checkout(String cartNumber,String customerNumber){
        Cart cart=cartDAO.findById(cartNumber).get();
        if(cart==null)return;
        cartDAO.delete(cart);
        kafkaSender.send("Checkout",checkoutAdapter.toDTO(new Checkout(customerNumber,cart)));

    }
    public Optional<CartDTO> getCart(String cartNumber){
        return cartDAO.findById(cartNumber).map(cartAdapter::toDTO);
    }


}
