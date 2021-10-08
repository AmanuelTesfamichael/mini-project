package com.example.shoppingcart.repository;

import com.example.shoppingcart.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartDAO extends CrudRepository<Cart,String> {
}
