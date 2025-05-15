package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.CartAdapter;
import com.example.project2.models.CartItem;
import com.example.project2.utils.SharedPreferencesUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PaymentSuccessActivity extends AppCompatActivity {

    private TextView confirmationText, cardholderText, dateText, timeText, amountText;
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private Button backToShopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        // אתחול רכיבים
        confirmationText = findViewById(R.id.confirmation_text);
        cardholderText = findViewById(R.id.card_holder_text);
        dateText = findViewById(R.id.date_text);
        timeText = findViewById(R.id.time_text);
        amountText = findViewById(R.id.amount_text);
        cartRecyclerView = findViewById(R.id.cart_recyclerview);
        backToShopButton = findViewById(R.id.back_to_shop_button);

        // קבלת נתונים מה-Intent
        double totalAmount = getIntent().getDoubleExtra("total_amount", 0.0);
        String cardHolder = getIntent().getStringExtra("card_holder");
        List<CartItem> cartItems = SharedPreferencesUtil.loadCart(this);

        // הצגת פרטי תשלום
        confirmationText.setText("Payment Successful!");
        cardholderText.setText("Cardholder: " + cardHolder);
        amountText.setText("Total Amount: ₪" + totalAmount);

        // תאריך ושעה
        Date now = new Date();
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(now);
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(now);
        dateText.setText("Date: " + currentDate);
        timeText.setText("Time: " + currentTime);

        // RecyclerView
        cartAdapter = new CartAdapter(this);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        if (cartItems != null) {
            cartAdapter.setItems(cartItems);
        }

        // מעבר לדף הנעליים
        backToShopButton.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentSuccessActivity.this, ShoesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
