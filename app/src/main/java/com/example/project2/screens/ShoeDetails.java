package com.example.project2.screens;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;

public class ShoeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);

        ImageView shoeImage = findViewById(R.id.shoe_detail_image);
        TextView shoeName = findViewById(R.id.shoe_detail_name);
        TextView shoePrice = findViewById(R.id.shoe_detail_price);

        // קבלת נתונים מה-Intent
        String name = getIntent().getStringExtra("shoe_name");
        double price = getIntent().getDoubleExtra("shoe_price", 0);
        int imageResId = getIntent().getIntExtra("shoe_image", 0);

        // הצגת הנתונים על המסך
        shoeName.setText(name);
        shoePrice.setText("$" + price);
        shoeImage.setImageResource(imageResId);
    }
}
