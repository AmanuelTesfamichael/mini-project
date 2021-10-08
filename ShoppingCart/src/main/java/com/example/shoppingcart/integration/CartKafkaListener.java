package com.example.shoppingcart.integration;

import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.dto.CartLineDTO;
import com.example.shoppingcart.service.dto.CheckoutDTO;
import com.example.shoppingcart.service.dto.ProductDTO;
import com.example.shoppingcartcommandservice.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CartKafkaListener {
    private static final Logger logger = LoggerFactory.getLogger(CartKafkaListener.class);
    private CartService cartService;
    private KafkaSender kafkaSender;

    @Autowired
    public CartKafkaListener(CartService cartService, KafkaSender kafkaSender) {
        this.cartService = cartService;
        this.kafkaSender = kafkaSender;
    }

    @KafkaListener(topics = {"CartProductEventtopic1"})
    public void receive(@Payload String event){
        logger.info(String.format("Kafka event %s",event));
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            JsonNode json=objectMapper.readTree(event);
            String eventName=json.get("eventName").textValue();
            if(eventName.equals("Product Added")){
                ProductAdded productAdded=objectMapper.readValue(event,ProductAdded.class);
                cartService.addToCart(productAdded.getCartNumber(),toCartLineDTO(productAdded.getCartLine()));
            }
            else if(eventName.equals("Product Removed")){
                ProductRemoved removed=objectMapper.readValue(event,ProductRemoved.class);
                cartService.removeProduct(removed.getCartNumber(),removed.getProductNumber());
            }
            else if(eventName.equals("Quantity Changed")){
                QuantityChanged changed=objectMapper.readValue(event,QuantityChanged.class);
                cartService.changeQuantity(changed.getCartNumber(),changed.getProductNumber(),changed.getQuantity());
            }
            else if(eventName.equals("Checkout")){
                Checkout checkout=objectMapper.readValue(event,Checkout.class);
                kafkaSender.send("Order",new CheckoutDTO(checkout.getCustomerNumber(),cartService.getCart(checkout.getCartNumber()).get()));
                cartService.checkout(checkout.getCartNumber(),checkout.getCartNumber());
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private CartLineDTO toCartLineDTO(CartLine cartLine){
        return new CartLineDTO(cartLine.getQuantity(),toProductDTO(cartLine.getProduct()));
    }
    private ProductDTO toProductDTO(Product product){
        return new ProductDTO(product.getProductNumber(),product.getProductName(),product.getProductDescription(),product.getProductPrice());
    }
}
