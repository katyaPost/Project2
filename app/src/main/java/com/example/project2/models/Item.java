package com.example.project2.models;

// מחלקת פריט
class Item {
    protected  String id;
    private String name;
    private double price;

    // קונסטרקטור
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // הדפסת פרטי הפריט
    public void printItemDetails() {
        System.out.println(name + " - Price: $" + price);
    }
}
