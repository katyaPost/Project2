package com.example.project2.screens;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project2.R;
import com.example.project2.adapters.CartAdapter;
import com.example.project2.adapters.CheckoutPagerAdapter;
import com.example.project2.models.CartItem;
import com.example.project2.utils.SharedPreferencesUtil;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private int currentStep = 0;
    private LinearLayout stepIndicator;
    private EditText cardInput, cardholderName, cvcInput, expiryDateInput;

    private RecyclerView summaryRecyclerView;
    private TextView totalPriceTextView;
    private CartAdapter cartAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        viewPager = findViewById(R.id.view_pager);
        stepIndicator = findViewById(R.id.step_indicator);

        cardInput = findViewById(R.id.card_input);
        cardholderName = findViewById(R.id.cardholder_name);
        cvcInput = findViewById(R.id.cvc_input);
        expiryDateInput = findViewById(R.id.expiry_date);

        summaryRecyclerView = findViewById(R.id.payment_success_recyclerview);
        totalPriceTextView = findViewById(R.id.total_price_text_view);

        CheckoutPagerAdapter adapter = new CheckoutPagerAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                currentStep = position;
                updateStepIndicator();

                if (currentStep == 1) {
                    showCartSummary(); // ברגע שעוברים לשלב 2
                }
            }
        });

        updateStepIndicator();
    }

    private void updateStepIndicator() {
        View step1 = findViewById(R.id.circle_step_1);
        View step2 = findViewById(R.id.circle_step_2);

        step1.setBackgroundResource(currentStep == 0 ? R.drawable.circle_step_active : R.drawable.circle_step_inactive);
        step2.setBackgroundResource(currentStep == 1 ? R.drawable.circle_step_active : R.drawable.circle_step_inactive);
    }

    public void onNextClicked(View view) {
        if (currentStep == 0) {
            if (isInputValid()) {
                viewPager.setCurrentItem(1);
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
            SharedPreferencesUtil.clearCart(this); // נקה את העגלה אחרי אישור
        }
    }

    private boolean isInputValid() {
        return !cardInput.getText().toString().isEmpty() &&
                !cardholderName.getText().toString().isEmpty() &&
                !cvcInput.getText().toString().isEmpty() &&
                !expiryDateInput.getText().toString().isEmpty();
    }

    private void showCartSummary() {
        List<CartItem> cartItems = SharedPreferencesUtil.loadCart(this);
        if (cartAdapter == null) {
            cartAdapter = new CartAdapter(this);
            summaryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            summaryRecyclerView.setAdapter(cartAdapter);
        }
        cartAdapter.setItems(cartItems);

        double totalPrice = 0;
        for (CartItem item : cartItems) {
            totalPrice += item.getShoePrice();
        }

        totalPriceTextView.setText("Total Price: $" + String.format("%.2f", totalPrice));
    }
}
