package com.example.productservice.service.adapter;

import com.example.productservice.domain.Product;
import com.example.productservice.service.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductAdapter {
    public ProductDTO toDTO(Product product){
        return new ProductDTO(product.getProductNumber(),product.getProductName(),product.getProductPrice(),product.getProductDescription(),product.getAvailable());
    }
    public Product fromDTO(ProductDTO productDTO){
        return new Product(productDTO.getProductNumber(),productDTO.getProductName(),productDTO.getProductPrice(),productDTO.getProductDescription(),productDTO.getAvailable());
    }
}
