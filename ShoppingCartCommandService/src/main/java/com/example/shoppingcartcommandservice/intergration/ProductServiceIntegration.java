package com.example.shoppingcartcommandservice.intergration;

import com.example.shoppingcartcommandservice.intergration.dto.ProductServiceProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ProductService")
@Service
public interface ProductServiceIntegration {
    @RequestMapping("/product/{productNumber}")
    ProductServiceProduct getProduct(@PathVariable String productNumber);
}
