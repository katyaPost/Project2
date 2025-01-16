package com.example.project2.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected String id;

    private List<String> items;
    private List<String> prices;
    private List<Integer> images;

    public Cart() {
        items = new ArrayList<>();
        prices = new ArrayList<>();
        images = new ArrayList<>();
    }

    // עדכון הפונקציה כך שתוכל לקבל את ה-Image ID
    public void addItem(String id, String item, String price, int imageResId) {
        items.add(item);
        prices.add(price);
        images.add(imageResId); // שמירת ה-Image ID בעגלה
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<String> getPrices() {
        return prices;
    }

    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }
}
