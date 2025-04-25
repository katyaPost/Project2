// CartActivity.java
package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.CartAdapter;
import com.example.project2.models.Cart;

import java.io.Serializable;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cart_recyclerview);
        cartAdapter = new CartAdapter(this);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        enableSwipeToDelete(cartRecyclerView, cartAdapter);

        // טעינת הסל מה-SharedPreferences
        loadCartFromPreferences();

        // מעבר לעמוד התשלום
        findViewById(R.id.checkout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // יצירת Intent לעמוד התשלום
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);

                // הוספת המידע של הסל כ-extra כדי שיהיה נגיש בעמוד התשלום
                intent.putExtra("cart_items", (Serializable) Cart.getInstance().getItems());
                startActivity(intent);
            }
        });
    }

    private void loadCartFromPreferences() {
        cartAdapter.loadCartFromPreferences();
    }

    public void enableSwipeToDelete(RecyclerView recyclerView, CartAdapter adapter) {
        // פעולה למחיקת פריט מהסל
    }
}
