package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.CartAdapter;
import com.example.project2.models.CartItem;
import com.example.project2.utils.SharedPreferencesUtil;

import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardNumberEditText, cardholderNameEditText, expiryDateEditText, cvcEditText;
    private TextView totalAmountTextView;
    private Button nextButton;
    private double totalAmount;
    private List<CartItem> cartItems;

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // קישור רכיבים
        cardNumberEditText = findViewById(R.id.card_input);
        cardholderNameEditText = findViewById(R.id.cardholder_name);
        expiryDateEditText = findViewById(R.id.expiry_date);
        cvcEditText = findViewById(R.id.cvc_input);
        totalAmountTextView = findViewById(R.id.total_amount_text);
        nextButton = findViewById(R.id.NEXT);
        cartRecyclerView = findViewById(R.id.payment_cart_recyclerview); // חדש

        // קבלת נתונים מה-Intent
        totalAmount = getIntent().getDoubleExtra("total_amount", 0.0);
        cartItems = SharedPreferencesUtil.loadCart(this);

        Log.d("!!!!!!!!!!!!!!!!!!!!!!", "size: "+cartItems.size());

        // הצגת סכום כולל
        totalAmountTextView.setText("Total: ₪" + totalAmount);

        // הצגת העגלה ב־RecyclerView
        setupCartRecyclerView();

        // מעבר למסך הצלחה
        nextButton.setOnClickListener(v -> {
            String cardHolderName = cardholderNameEditText.getText().toString().trim();

            Intent intent = new Intent(PaymentActivity.this, PaymentSuccessActivity.class);
            intent.putExtra("total_amount", totalAmount);
            intent.putExtra("card_holder", cardHolderName);
            startActivity(intent);
        });
    }

    private void setupCartRecyclerView() {
        cartAdapter = new CartAdapter(this);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);
        cartAdapter.setItems(cartItems);
    }
}
