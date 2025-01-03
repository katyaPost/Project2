package com.example.project2.screens;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ColorsAdapter;

import java.util.ArrayList;

public class ShoeDetails extends AppCompatActivity {

    private ImageView shoeImage;
    private RecyclerView colorsRecyclerView;
    private ColorsAdapter colorsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);

        shoeImage = findViewById(R.id.shoe_detail_image);
        TextView shoeName = findViewById(R.id.shoe_detail_name);
        TextView shoePrice = findViewById(R.id.shoe_detail_price);
        colorsRecyclerView = findViewById(R.id.additional_colors_recyclerview);

        // קבלת נתונים מה-Intent
        String name = getIntent().getStringExtra("shoe_name");
        double price = getIntent().getDoubleExtra("shoe_price", 0);
        int imageResId = getIntent().getIntExtra("shoe_image", 0);
        ArrayList<Integer> colorOptions = getIntent().getIntegerArrayListExtra("shoe_colors");

        // הצגת נתוני הנעל
        shoeName.setText(name);
        shoePrice.setText("$" + price);
        shoeImage.setImageResource(imageResId);

        // הגדרת RecyclerView להצגת הצבעים הנוספים
        colorsAdapter = new ColorsAdapter(this, colorOptions, selectedColor -> {
            // שינוי התמונה הראשית לפי הצבע שנבחר
            shoeImage.setImageResource(selectedColor);
        });

        colorsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        colorsRecyclerView.setAdapter(colorsAdapter);
    }
}
