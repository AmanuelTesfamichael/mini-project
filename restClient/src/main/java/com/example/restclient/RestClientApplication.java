package com.example.restclient;

import com.example.restclient.cartcommand.CartLineDTO;
import com.example.restclient.product.ProductDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {

    private RestTemplate restTemplate;
    private String url="http://localhost:8080";
    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restTemplate=new RestTemplate();
        //adding a product
        ProductDTO product = new ProductDTO("123","Shoe",120,"Something",120);
        ProductDTO product2 = new ProductDTO("234", "Sandal", 12,"slippery",100);

        addProduct(product);
        addProduct(product2);

        //modify product
        ProductDTO modifiedProduct2 = new ProductDTO(product2.getProductNumber(),product2.getProductName(),12,"Not slippery",100);
        restTemplate.put(url+"/product",modifiedProduct2)
        ;
        //get product 2
        System.out.println(restTemplate.getForObject(url+"/product/234",ProductDTO.class));

        //add to cart
        restTemplate.postForLocation(url+"/cartcommand/123",new CartLineDTO(2,new com.example.restclient.cartcommand.ProductDTO(product2.getProductNumber(),product2.getProductName(),product2.getProductDescription(),product2.getProductPrice())));
        //add a second product to the shopping cart
        restTemplate.postForLocation(url+"/cartcommand/123",new CartLineDTO(2,new com.example.restclient.cartcommand.ProductDTO(product.getProductNumber(),product.getProductName(),product.getProductDescription(),product.getProductPrice())));

        //delete the second product
        restTemplate.delete(url+"/cartcommand/123/234");

        //change the first quantity to 1
        restTemplate.put(url+"/cartcommand/123/123/1",null);

        //show shopping cart
        System.out.println(restTemplate.getForObject(url+"/cartquery/123",String.class));

        //finally checkout
        restTemplate.postForLocation(url+"/cartcommand/123/checkout/abc",null);

    }
    public void editProduct(ProductDTO product){
        restTemplate.postForLocation(url+"/product",product);
    }
    public void addProduct(ProductDTO product){
        restTemplate.postForLocation(url+"/product",product);
    }
}
