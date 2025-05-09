package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardNumberEditText, cardholderNameEditText, expiryDateEditText, cvcEditText;
    private TextView orderSummaryTextView, totalAmountTextView;
    private Button nextButton;
    private double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // אתחול שדות
        cardNumberEditText = findViewById(R.id.card_input);
        cardholderNameEditText = findViewById(R.id.cardholder_name);
        expiryDateEditText = findViewById(R.id.expiry_date);
        cvcEditText = findViewById(R.id.cvc_input);
        orderSummaryTextView = findViewById(R.id.cart_summary_text);
        totalAmountTextView = findViewById(R.id.total_amount_text);
        nextButton = findViewById(R.id.NEXT);  // ודאי שזו האות הגדולה

        // קבלת סכום מהIntent
        totalAmount = getIntent().getDoubleExtra("total_amount", 0.0);
        totalAmountTextView.setText("Total: ₪" + totalAmount);
        orderSummaryTextView.setText("Order Summary:\n...");

        nextButton.setOnClickListener(v -> {
            String cardHolderName = cardholderNameEditText.getText().toString().trim();

            Intent intent = new Intent(PaymentActivity.this, PaymentSuccessActivity.class);
            intent.putExtra("total_amount", totalAmount);
            intent.putExtra("card_holder", cardHolderName);
            startActivity(intent);
        });
    }
}
