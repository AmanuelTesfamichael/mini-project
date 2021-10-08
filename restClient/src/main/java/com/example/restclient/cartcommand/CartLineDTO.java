package com.example.restclient.cartcommand;

public class CartLineDTO {
    private int quantity;
    private ProductDTO product;

    public CartLineDTO() {
    }

    public CartLineDTO(int quantity, ProductDTO product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }
}
