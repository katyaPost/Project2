package com.example.project2.models;

public class Shoe {
    // מחלקת נעל
        // שדות שמאפיינים את הנעל
        private String brand;
        private String color;
        private double size;
        private double price;

        // קונסטרקטור - יוצרת נעל חדשה
        public Shoe(String brand, String color, double size, double price) {
            this.brand = brand;
            this.color = color;
            this.size = size;
            this.price = price;
        }

        // Getters ו-Setters כדי לגשת לשדות ולשנות אותם
        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        // מתודה להדפסת פרטי הנעל
        public void printShoeDetails() {
            System.out.println("Brand: " + brand);
            System.out.println("Color: " + color);
            System.out.println("Size: " + size);
            System.out.println("Price: $" + price);
        }



    }

