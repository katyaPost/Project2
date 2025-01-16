package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.project2.R;
import com.example.project2.models.Cart;

public class AddToCartActivity extends AppCompatActivity {

    // אובייקט של העגלה
    private Cart cart = new Cart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        // חיבור ה-Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // חיבור הכפתור לתכנה
        Button addToCartButton = findViewById(R.id.add_to_cart_button);

        // חיבור אל תצוגת הנעל (שם, מחיר, תמונה)
        TextView shoeNameTextView = findViewById(R.id.shoe_name);
        TextView shoePriceTextView = findViewById(R.id.shoe_price);

        // נתוני הנעל
        final String shoeName = "Old Skool";
        final String shoePrice = "₪349.90";
        final int shoeImageResId = R.drawable.shoe_blue; // נתון לתמונה של הנעל

        // כאשר לוחצים על כפתור "הוסף לעגלה"
//        addToCartButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // הוספת הנעל לעגלה עם פרטי המוצר
//                cart.addItem(shoeName, shoePrice, shoeImageResId);
//
//                // הצגת הודעת Toast על הוספת המוצר
//                Toast.makeText(AddToCartActivity.this, shoeName + " ADDED!!!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    // יצירת תפריט
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    // טיפול בלחיצה על פריט בתפריט
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {
            // מעבר לדף סל הקניות
            Intent intent = new Intent(this, CartActivity.class); // לוודא ש-CartActivity קיים
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
