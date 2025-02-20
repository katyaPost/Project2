package com.example.project2.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private static Cart instance;
    private List<CartItem> items;
    private String id;



    private Cart() {
        items = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }
    public static void setInstance(Cart instance) {
        Cart.instance = instance;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }
}
