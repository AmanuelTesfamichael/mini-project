package com.example.productservice.web;

import com.example.productservice.service.dto.ProductDTO;

public class Products {
    private Iterable<ProductDTO> products;

    public Products(Iterable<ProductDTO> products) {
        this.products = products;
    }

    public Iterable<ProductDTO> getProducts() {
        return products;
    }
}
