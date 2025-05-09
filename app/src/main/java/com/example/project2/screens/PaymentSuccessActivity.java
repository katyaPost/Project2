package com.example.project2.screens;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentSuccessActivity extends AppCompatActivity {

    private TextView confirmationText, cardholderText, dateText, amountText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        confirmationText = findViewById(R.id.confirmation_text);
        cardholderText = findViewById(R.id.card_holder_text);
        dateText = findViewById(R.id.date_text);
        amountText = findViewById(R.id.amount_text);

        // קבלת פרטי ההזמנה מהIntent
        double totalAmount = getIntent().getDoubleExtra("total_amount", 0.0);
        String cardHolder = getIntent().getStringExtra("card_holder");

        // הצגת הפרטים
        confirmationText.setText("Payment Successful!");
        cardholderText.setText("Cardholder: " + cardHolder);
        amountText.setText("Total Amount: ₪" + totalAmount);

        // הצגת התאריך הנוכחי
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        dateText.setText("Date: " + currentDate);
    }
}
