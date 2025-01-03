package com.example.project2.models;

import java.util.List;

public class Shoe {
    private String name;
    private double price;
    private String gender;
    private int imageResId;
    private List<Integer> colorOptions; // רשימה של תמונות צבעים

    public Shoe(String name, double price, String gender, int imageResId, List<Integer> colorOptions) {
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.imageResId = imageResId;
        this.colorOptions = colorOptions;
    }

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

    public List<Integer> getColorOptions() {
        return colorOptions;
    }
}
