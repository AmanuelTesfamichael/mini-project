package com.example.productservice.web;

import com.example.productservice.service.ProductService;
import com.example.productservice.service.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RequestMapping("/product")
@RestController
public class ProductController {
    private ProductService productService;
    private static final Logger logger= LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO productDTO){
        logger.info(String.format("Creating a single product %s",productDTO));
        return productService.create(productDTO);
    }
    @GetMapping("/{productNumber}")
    public ResponseEntity<?> get(@PathVariable String productNumber){
        logger.info("getting a single product");
        return productService.getByProductNumber(productNumber).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping
    public  ResponseEntity<?> edit(@RequestBody ProductDTO productDTO){
        logger.info("editing product");
        productService.edit(productDTO);
        return ResponseEntity.ok("");
    }
    @DeleteMapping("/{productNumber}")
    public ResponseEntity<?> delete(@PathVariable String productNumber){
        logger.info(String.format("Deleting product with product number %s",productNumber));
        productService.delete(productNumber);
        return ResponseEntity.ok("Deleted");
    }
    @GetMapping
    public Products getAll(){
        logger.info("getting all products");
        return new Products(productService.getAll());
    }
}
