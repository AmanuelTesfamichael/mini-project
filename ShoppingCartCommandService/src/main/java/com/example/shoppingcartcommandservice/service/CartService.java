package com.example.shoppingcartcommandservice.service;

import com.example.shoppingcartcommandservice.domain.*;
import com.example.shoppingcartcommandservice.intergration.KafkaSender;
import com.example.shoppingcartcommandservice.intergration.ProductServiceIntegration;
import com.example.shoppingcartcommandservice.repository.CartEventDAO;
import com.example.shoppingcartcommandservice.service.adapter.CartLineAdapter;
import com.example.shoppingcartcommandservice.service.adapter.ProductAdapter;
import com.example.shoppingcartcommandservice.service.dto.CartLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional()
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private CartEventDAO cartEventDAO;
    private CartLineAdapter cartLineAdapter;
    private ProductAdapter productAdapter;
    private KafkaSender kafkaSender;

    private static final String CART_PRODUCT_EVENT_TOPIC = "CartProductEventtopic1";

    @Autowired
    public CartService(CartEventDAO cartEventDAO, CartLineAdapter cartLineAdapter, ProductAdapter productAdapter, ProductServiceIntegration productServiceIntegration, KafkaSender kafkaSender) {
        this.cartEventDAO = cartEventDAO;
        this.cartLineAdapter = cartLineAdapter;
        this.productAdapter = productAdapter;
        this.productServiceIntegration = productServiceIntegration;
        this.kafkaSender = kafkaSender;
    }
    public void addToCart(String cartNumber,CartLineDTO cartLine){
        if(getStockQuantity(cartLine.getProduct().getProductNumber())<cartLine.getQuantity())throw new OutOfStockException();
        else{
            CartEvent event = new ProductAdded(cartLineAdapter.fromDTO(cartLine),cartNumber);
            cartEventDAO.save(event);
            kafkaSender.send(CART_PRODUCT_EVENT_TOPIC,event);
        }
    }
    public void changeQuantity(String cartNumber,String productNumber, int newQuantity)throws OutOfStockException{
        if(getStockQuantity(productNumber)<newQuantity)throw new OutOfStockException();
        else{
            QuantityChanged quantityChanged = new QuantityChanged(productNumber,newQuantity,cartNumber);
            cartEventDAO.save(quantityChanged);

            kafkaSender.send(CART_PRODUCT_EVENT_TOPIC,quantityChanged);
        }
    }
    private int getStockQuantity(String productNumber){
        return Optional.of(productServiceIntegration.getProduct(productNumber)).map(productServiceProduct -> productServiceProduct.getAvailable()).orElse(0);
    }
    public void removeProduct(String cartNumber,String productNumber){
        ProductRemoved productRemoved = new ProductRemoved(productNumber,cartNumber);
        cartEventDAO.save(productRemoved);

        kafkaSender.send(CART_PRODUCT_EVENT_TOPIC,productRemoved);
    }
    public void checkOut(String cartNumber,String customerNumber){
        Checkout checkout = new Checkout(cartNumber,customerNumber);
        cartEventDAO.save(checkout);

        kafkaSender.send(CART_PRODUCT_EVENT_TOPIC, checkout);
    }
}
