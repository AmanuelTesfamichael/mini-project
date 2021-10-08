package com.example.productservice.service;

import com.example.productservice.respository.ProductDAO;
import com.example.productservice.service.adapter.ProductAdapter;
import com.example.productservice.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductAdapter productAdapter;
    private ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO, ProductAdapter productAdapter) {
        this.productDAO = productDAO;
        this.productAdapter = productAdapter;
    }
    public ProductDTO create(ProductDTO product){
        return productAdapter.toDTO(productDAO.save(productAdapter.fromDTO(product)));
    }
    public void edit(ProductDTO product){
        productDAO.save(productAdapter.fromDTO(product));
    }
    public void addToStock(String productNumber, int stockValue){
        productDAO.findById(productNumber).ifPresent(product -> {
            product.add(stockValue);
            productDAO.save(product);
        });
    }
    public void removeFromStock(String productNumber, int stockValue){
        productDAO.findById(productNumber).ifPresent(product -> {
            product.take(stockValue);
            productDAO.save(product);
        });
    }
    public Iterable<ProductDTO> getAll(){
        return StreamSupport.stream(productDAO.findAll().spliterator(),false).map(productAdapter::toDTO).collect(Collectors.toList());
    }
    public Optional<ProductDTO> getByProductNumber(String productNumber){
        return productDAO.findById(productNumber).map(productAdapter::toDTO);
    }
    public void delete(String productNumber){
        productDAO.deleteById(productNumber);
    }
}
