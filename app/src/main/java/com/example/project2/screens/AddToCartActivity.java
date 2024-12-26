package com.example.project2.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;
import com.example.project2.models.Cart;

public class AddToCartActivity extends AppCompatActivity {

    // אובייקט של העגלה
    private Cart cart = new Cart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        // חיבור הכפתור לתכנה
        Button addToCartButton = findViewById(R.id.add_to_cart_button);

        // חיבור אל תצוגת הנעל (שם, מחיר, תמונה)
        TextView shoeNameTextView = findViewById(R.id.shoe_name);
        TextView shoePriceTextView = findViewById(R.id.shoe_price);


        // נתוני הנעל
        final String shoeName = "old skool";
        final String shoePrice = "₪349.90";


        // כאשר לוחצים על כפתור "הוסף לעגלה"
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // הוספת הנעל לעגלה
                cart.addItem(shoeName, shoePrice);

                // הצגת הודעת Toast על הוספת המוצר
                Toast.makeText(AddToCartActivity.this, shoeName + " ADDED!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
