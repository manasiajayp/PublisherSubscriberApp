package com.example.Producer.model;

import java.io.Serializable;

public class Order implements Serializable {

    private String id;
    private String item;
    private int quantity;

    // Default constructor (needed for Redis serialization)
    public Order() {
    }

    // Constructor with fields
    public Order(String id, String item, int quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

