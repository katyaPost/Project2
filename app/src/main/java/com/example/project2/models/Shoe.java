package com.example.project2.models;

public class Shoe {
    private String name;
    private double price;
    private String gender;
    private int imageResId;

    public Shoe(String name, double price, String gender, int imageResId) {
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.imageResId = imageResId;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getGender() {
        return gender;
    }

    public int getImageResId() {
        return imageResId;
    }
}
