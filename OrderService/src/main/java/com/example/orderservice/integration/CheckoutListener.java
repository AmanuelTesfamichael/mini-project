package com.example.orderservice.integration;

import com.example.orderservice.domain.Customer;
import com.example.orderservice.domain.Order;
import com.example.orderservice.domain.OrderLine;
import com.example.orderservice.domain.Product;
import com.example.orderservice.integration.dto.CartDTO;
import com.example.orderservice.integration.dto.CheckoutDTO;
import com.example.orderservice.integration.dto.ProductDTO;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckoutListener {
    private OrderService orderService;

    @Autowired
    public CheckoutListener(OrderService orderService) {
        this.orderService = orderService;
    }
    @KafkaListener(topics = "Order")
    public void add(@Payload String event){
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            CheckoutDTO checkout=objectMapper.readValue(event,CheckoutDTO.class);
            orderService.addOrder(toOrder(checkout));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private Order toOrder(CheckoutDTO checkoutDTO){
        List<OrderLine> orders=checkoutDTO.getCart().getCartLines()
                .stream()
                .map(cartLineDTO -> new OrderLine(cartLineDTO.getQuantity(),toProduct(cartLineDTO.getProduct())))
                .collect(Collectors.toList());
        return new Order(orders,new Customer(checkoutDTO.getCustomerNumber()));
    }
    private Product toProduct(ProductDTO productDTO){
        return new Product(productDTO.getProductNumber(),productDTO.getProductName());
    }
}
