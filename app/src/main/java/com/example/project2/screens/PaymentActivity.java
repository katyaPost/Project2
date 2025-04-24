package com.example.project2.screens;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    private TextView cardHolderText, dateText, amountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardHolderText = findViewById(R.id.card_holder_text);
        dateText = findViewById(R.id.date_text);
        amountText = findViewById(R.id.amount_text);

        // קבלת פרטים מה-Intent
        String cardHolder = getIntent().getStringExtra("card_holder");
        double totalAmount = getIntent().getDoubleExtra("total_amount", 0.0);

        // תאריך נוכחי
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        // הצגת הפרטים
        cardHolderText.setText("Cardholder: " + cardHolder);
        dateText.setText("Date: " + currentDate);
        amountText.setText("Total Amount: $" + totalAmount);
    }
}
