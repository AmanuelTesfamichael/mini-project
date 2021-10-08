package com.example.shoppingcartcommandservice.repository;

import com.example.shoppingcartcommandservice.domain.CartEvent;
import org.springframework.data.repository.CrudRepository;

public interface CartEventDAO extends CrudRepository<CartEvent,String> {
}
