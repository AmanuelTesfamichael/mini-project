package com.example.shoppingcartcommandservice.domain;


import java.util.Date;

public abstract class CartEvent {
    private Date date;
    private String cartNumber;
    private String eventName;

    private String eventID;
    public CartEvent() {
    }

    public CartEvent(String eventName,String cartNumber) {
        date=new Date();
        this.eventName=eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public String getCartNumber() {
        return cartNumber;
    }
}
