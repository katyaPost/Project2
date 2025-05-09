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

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private TextView totalPriceTextView;
    private Button confirmOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // אתחול רכיבי UI
        cartRecyclerView = findViewById(R.id.cart_recyclerview);
        totalPriceTextView = findViewById(R.id.total_price_text_view);
        confirmOrderButton = findViewById(R.id.confirm_order_button);

        // טוען את פריטי העגלה
        List<CartItem> cartItems = SharedPreferencesUtil.loadCart(this);

        // אתחול והגדרת מתאם לעגלה
        CartAdapter cartAdapter = new CartAdapter(this);
        cartAdapter.setItems(cartItems);
        cartRecyclerView.setAdapter(cartAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // חישוב המחיר הכולל
        double total = cartItems.stream().mapToDouble(CartItem::getShoePrice).sum();

        // הצגת המחיר הכולל
        totalPriceTextView.setText("Total Price: ₪" + total);

        // מעבר לדף תשלום
        confirmOrderButton.setOnClickListener(v -> {
            // יצירת Intent והעברת total_amount
            Intent intent = new Intent(CheckoutActivity.this, PaymentActivity.class);

            // העברת הסכום הכולל לדף התשלום
            intent.putExtra("total_amount", total);  // העברת סכום כולל לדף התשלום

            // ביצוע ה-startActivity
            startActivity(intent);
        });
    }
}
