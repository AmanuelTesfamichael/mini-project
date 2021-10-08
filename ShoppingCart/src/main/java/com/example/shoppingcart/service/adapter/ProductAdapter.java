package com.example.shoppingcart.service.adapter;

import com.example.shoppingcart.domain.Product;
import com.example.shoppingcart.service.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductAdapter {
    public ProductDTO toDTO(Product product){
        return new ProductDTO(product.getProductNumber(),product.getProductName(),product.getProductDescription(),product.getPrice());
    }
    public Product fromDTO(ProductDTO product){
        return new Product(product.getProductNumber(), product.getProductName(),product.getProductDescription(),product.getPrice());
    }
}
