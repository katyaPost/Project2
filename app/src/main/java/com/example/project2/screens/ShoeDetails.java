package com.example.project2.screens;

import android.os.Bundle;
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
import com.example.project2.models.Shoe;
import com.example.project2.models.ShoeColor;
import com.example.project2.services.DatabaseService;
import com.example.project2.utils.ImageUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoeDetails extends AppCompatActivity {

    String shoeId;
    private ImageView shoeImage;
    private RecyclerView colorsRecyclerView;
    private ColorsAdapter colorsAdapter;
    private Spinner sizeSpinner;

    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);

        databaseService = DatabaseService.getInstance();

        shoeImage = findViewById(R.id.shoe_detail_image);
        TextView shoeName = findViewById(R.id.shoe_detail_name);
        TextView shoePrice = findViewById(R.id.shoe_detail_price);
        colorsRecyclerView = findViewById(R.id.additional_colors_recyclerview);
        sizeSpinner = findViewById(R.id.shoe_size_spinner);

        // קבלת נתונים מה-Intent
        shoeId = getIntent().getStringExtra("shoe_id");
        if (shoeId == null) {
            finish();
            return;
        }

        databaseService.getShoe(shoeId, new DatabaseService.DatabaseCallback<Shoe>() {
            @Override
            public void onCompleted(Shoe shoe) {
                // הצגת נתוני הנעל
                shoeName.setText(shoe.getName());
                shoePrice.setText("$" + shoe.getPrice());
                shoeImage.setImageBitmap(ImageUtil.convertFrom64base(
                        shoe.getColorOptions().get(0).getPicBase64()));

                List<ShoeColor> colorOptions = shoe.getColorOptions();

//                // הגדרת RecyclerView להצגת הצבעים הנוספים
//                colorsAdapter = new ColorsAdapter(this, colorOptions, selectedColor -> {
//                    // שינוי התמונה הראשית לפי הצבע שנבחר
//                    shoeImage.setImageResource(selectedColor);
//                });
//
//                colorsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//                colorsRecyclerView.setAdapter(colorsAdapter);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });







        // הגדרת Spinner עם רשימת המידות
        setupSizeSpinner();
    }

    private void setupSizeSpinner() {
        // רשימת מידות לדוגמה
        List<String> sizes = Arrays.asList("Select Size", "35", "36", "37", "38", "39", "40", "41", "42", "43");

        // יצירת ArrayAdapter עבור Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);

        // Listener לבחירת מידה
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedSize = parent.getItemAtPosition(position).toString();
                if (!selectedSize.equals("Select Size")) {
                    Toast.makeText(ShoeDetails.this, "Selected size: " + selectedSize, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // פעולה במקרה שלא נבחר דבר
            }
        });
    }
}
