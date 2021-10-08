package com.example.shoppingcartcommandservice.domain;

public class CartLine {
    private int quantity;
    private com.example.shoppingcartcommandservice.domain.Product product;

    public CartLine() {
    }

    public CartLine(int quantity, com.example.shoppingcartcommandservice.domain.Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public com.example.shoppingcartcommandservice.domain.Product getProduct() {
        return product;
    }
}
