package com.example.project2.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Shoe shoe;
    private double size;
    private ShoeColor color;

    public CartItem(Shoe shoe, double size, ShoeColor color) {
        this.shoe = shoe;
        this.size = size;
        this.color = color;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public double getSize() {
        return size;
    }

    public ShoeColor getColor() {
        return color;
    }

    public String getShoeName() {
        return shoe.getName();
    }

    public double getShoePrice() {
        return shoe.getPrice();
    }

    public String getShoeColorName() {
        return color.getColorName();
    }

    public String getShoeImageBase64() {
        return color.getPicBase64();
    }

    // מתודה חדשה להחזרת ה-ID של הנעל מתוך האובייקט Shoe
    public String getId() {
        return shoe != null ? shoe.getId() : null;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "shoe=" + shoe +
                ", size=" + size +
                ", color=" + color +
                '}';
    }
}
