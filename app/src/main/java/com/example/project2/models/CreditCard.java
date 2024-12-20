package com.example.project2.models;

public class CreditCard {
    private String cardNumber;
    private String cardHolder;
    private String expirationDate; // תוקף הכרטיס
    private String cvv; // קוד CVV
    private double amount; // שדה של amount (סכום התשלום)


    // קונסטרקטור
    public CreditCard(String cardNumber, String cardHolder, String expirationDate, String cvv, double creditLimit) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;

    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }


    // מתודה לחיוב סכום מהכרטיס
    public boolean charge(double amount) {
        if (amount <= 0) {
            System.out.println("Amount to charge must be greater than 0.");
            return false;
        }
        else {
            System.out.println("Insufficient credit limit!");
        }
        return false;
    }


        // מתודה לביצוע תשלום עם כרטיס אשראי
    public boolean processPayment (){

        if (charge(amount)) {
            System.out.println("Payment of $" + amount + " was successful!");
            return true;
        }
        System.out.println("Payment failed.");
        return false;
    }

    // הדפסת פרטי כרטיס
    public void printCardDetails() {
        System.out.println("Cardholder: " + cardHolder);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiration Date: " + expirationDate);

    }

}
