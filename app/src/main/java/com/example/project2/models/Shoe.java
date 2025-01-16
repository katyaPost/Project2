package com.example.project2.models;

import java.util.List;
import java.util.Map;

public class Shoe {

    private String id;
    private String name;
    private double price;
    private String gender;
    private Map<String, String> colorOptions; // רשימה של צבעים -> תמונות

    public Shoe() {
    }

    public Shoe(String id, String name, double price, String gender, Map<String, String> colorOptions) {
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


    public Map<String, String> getColorOptions() {
        return colorOptions;
    }

    public void setColorOptions(Map<String, String> colorOptions) {
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
