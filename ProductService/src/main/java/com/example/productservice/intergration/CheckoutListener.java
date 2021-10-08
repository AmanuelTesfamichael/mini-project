package com.example.productservice.intergration;

import com.example.productservice.intergration.dto.CheckoutDTO;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CheckoutListener {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutListener.class);

    private ProductService productService;

    @Autowired
    public CheckoutListener(ProductService productService) {
        this.productService = productService;
    }

    @KafkaListener(topics = {"Checkout"})
    public void updateStock(String checkoutInfo){
        logger.info("Updating stock");
        try{
            CheckoutDTO checkout = new ObjectMapper().readValue(checkoutInfo,CheckoutDTO.class);
            checkout.getCart().getCartLines().forEach(cartLineDTO -> {
                productService.removeFromStock(cartLineDTO.getProduct().getProductNumber(),cartLineDTO.getQuantity());
            });
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }

    }
}
