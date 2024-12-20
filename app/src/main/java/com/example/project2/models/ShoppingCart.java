package com.example.project2.models;

import java.util.ArrayList;
import java.util.List;

// מחלקת סל קניות
class ShoppingCart {
    private List<Item> items;

    // קונסטרקטור - יוצר סל קניות ריק
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // מתודה להוסיף פריט לסל
    public void addItem(Item item) {
        items.add(item);
    }

    // מתודה להדפיס את כל הפריטים בסל
    public void printCartContents() {
        if (items.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Shopping Cart Contents:");
            for (Item item : items) {
                item.printItemDetails();
            }
        }
    }

    // מתודה לחשב את המחיר הכולל של כל הפריטים בסל
    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }


}
