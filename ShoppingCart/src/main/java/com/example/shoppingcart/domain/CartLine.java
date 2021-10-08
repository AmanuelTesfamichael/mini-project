package com.example.shoppingcart.domain;

public class CartLine {
    private Product product;
    private int quantity;

    public CartLine(Product product,int quantity) {
        this.product=product;
        this.quantity=quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    public void changeQuantity(int quantity){
        this.quantity=quantity;
    }
}
