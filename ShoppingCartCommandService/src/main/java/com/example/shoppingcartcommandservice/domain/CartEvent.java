package com.example.shoppingcartcommandservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public abstract class CartEvent {
    private Date date;
    private String cartNumber;
    private String eventName;

    @Id
    private String eventID;
    public CartEvent() {
    }

    public CartEvent(String eventName,String cartNumber) {
        date=new Date();
        this.eventName=eventName;
        this.cartNumber=cartNumber;
    }

    public String getEventName() {
        return eventName;
    }

    public String getCartNumber() {
        return cartNumber;
    }
}
