package com.example.productservice.respository;

import com.example.productservice.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product,String> {
}
