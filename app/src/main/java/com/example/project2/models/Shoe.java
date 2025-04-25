package com.example.project2.models;

import java.io.Serializable;  // ייבוא הממשק Serializable
import java.util.List;

public class Shoe implements Serializable {  // הוספת הממשק Serializable למחלקה Shoe

    private String id;
    private String name;
    private double price;
    private String gender;
    private List<ShoeColor> colorOptions;

    public Shoe() {
    }

    public Shoe(String id, String name, double price, String gender, List<ShoeColor> colorOptions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.colorOptions = colorOptions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<ShoeColor> getColorOptions() {
        return colorOptions;
    }

    public void setColorOptions(List<ShoeColor> colorOptions) {
        this.colorOptions = colorOptions;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", gender='" + gender + '\'' +
                ", colorOptions=" + colorOptions +
                '}';
    }
}
