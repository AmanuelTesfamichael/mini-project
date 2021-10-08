package com.example.shoppingcartcommandservice.domain;

public class CartLine {
    private int quantity;
    private Product product;

    public CartLine() {
    }

    public CartLine(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
