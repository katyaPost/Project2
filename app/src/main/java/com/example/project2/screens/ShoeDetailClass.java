package com.example.project2.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ColorsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoeDetailClass extends AppCompatActivity {

    private ImageView shoeImage;
    private RecyclerView colorsRecyclerView;
    private ColorsAdapter colorsAdapter;
    private Spinner sizeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);

        shoeImage = findViewById(R.id.shoe_detail_image);
        TextView shoeName = findViewById(R.id.shoe_detail_name);
        TextView shoePrice = findViewById(R.id.shoe_detail_price);
        colorsRecyclerView = findViewById(R.id.additional_colors_recyclerview);
        sizeSpinner = findViewById(R.id.shoe_size_spinner); // Spinner חדש

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
            shoeImage.setImageResource(selectedColor);
        });

        colorsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        colorsRecyclerView.setAdapter(colorsAdapter);

        // הגדרת Spinner עם רשימת המידות
        setupSizeSpinner();
    }

    private void setupSizeSpinner() {
        // רשימת מידות לדוגמה
        List<String> sizes = Arrays.asList("Select Size", "35", "36", "37", "38", "39", "40", "41", "42", "43");

        // יצירת Adapter עבור Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // חשוב לשים את זה!
        sizeSpinner.setAdapter(adapter);

        // Listener לבחירת מידה
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = parent.getItemAtPosition(position).toString();
                if (!selectedSize.equals("Select Size")) {
                    // עשי משהו עם המידה שנבחרה
                    Toast.makeText(ShoeDetailClass.this, "Selected size: " + selectedSize, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // פעולה במקרה שלא נבחר כלום
            }
        });
    }
}
