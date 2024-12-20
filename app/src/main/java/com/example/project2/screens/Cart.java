package com.example.project2.screens;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    // רשימה של מוצרים בעגלה
    private List<String> items;
    private List<String> prices;
    private List<Integer> images;

    public Cart() {
        items = new ArrayList<>();
        prices = new ArrayList<>();
        images = new ArrayList<>();
    }

    // הוספת מוצר לעגלה
    public void addItem(String item, String price) {
        items.add(item);
        prices.add(price);

    }

    // הצגת המוצרים בעגלה
    public List<String> getItems() {
        return items;
    }

    public List<String> getPrices() {
        return prices;
    }

    public List<Integer> getImages() {
        return images;
    }

    // הצגת סיכום של המוצרים בעגלה
    public String getCartSummary() {
        if (items.isEmpty()) {
            return "העגלה ריקה";
        } else {
            return "העגלה מכילה: " + String.join(", ", items);
        }
    }
}

