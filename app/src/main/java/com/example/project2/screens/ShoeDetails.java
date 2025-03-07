package com.example.project2.screens;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ColorsAdapter;
import com.example.project2.models.Cart;
import com.example.project2.models.CartItem;
import com.example.project2.models.Shoe;
import com.example.project2.models.ShoeColor;
import com.example.project2.services.DatabaseService;
import com.example.project2.utils.ImageUtil;
import com.example.project2.utils.SharedPreferencesUtil;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class ShoeDetails extends AppCompatActivity {

    private String shoeId;
    private Shoe currentShoe;
    private ShoeColor selectedColor;

    private ImageView shoeImage;
    private RecyclerView colorsRecyclerView;
    private ColorsAdapter colorsAdapter;
    private Spinner sizeSpinner;
    private Button addToCartButton;

    private DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);

        databaseService = DatabaseService.getInstance();

        shoeImage = findViewById(R.id.shoe_detail_image);
        TextView shoeName = findViewById(R.id.shoe_detail_name);
        TextView shoePrice = findViewById(R.id.shoe_detail_price);
        colorsRecyclerView = findViewById(R.id.additional_colors_recyclerview);
        colorsRecyclerView.setLayoutManager(new LinearLayoutManager(ShoeDetails.this, LinearLayoutManager.HORIZONTAL, false));
        sizeSpinner = findViewById(R.id.shoe_size_spinner);
        addToCartButton = findViewById(R.id.add_to_cart_button);

        shoeId = getIntent().getStringExtra("shoe_id");
        if (shoeId == null) {
            finish();
            return;
        }

        databaseService.getShoe(shoeId, new DatabaseService.DatabaseCallback<Shoe>() {
            @Override
            public void onCompleted(Shoe shoe) {
                currentShoe = shoe;
                shoeName.setText(shoe.getName());
                shoePrice.setText("$" + shoe.getPrice());

                if (!shoe.getColorOptions().isEmpty()) {
                    selectedColor = shoe.getColorOptions().get(0); // צבע ברירת מחדל
                    shoeImage.setImageBitmap(ImageUtil.convertFrom64base(selectedColor.getPicBase64()));
                }

                colorsAdapter = new ColorsAdapter(ShoeDetails.this, shoe.getColorOptions(), shoeColor -> {
                    selectedColor = shoeColor;
                    shoeImage.setImageBitmap(ImageUtil.convertFrom64base(shoeColor.getPicBase64()));
                });

                colorsRecyclerView.setAdapter(colorsAdapter);
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(ShoeDetails.this, "Failed to load shoe details", Toast.LENGTH_SHORT).show();
            }
        });

        setupSizeSpinner();
        setupAddToCartButton();
    }

    private void setupSizeSpinner() {
        List<String> sizes = Arrays.asList("Select Size", "35", "36","36.5", "37", "38","38.5", "39", "40","40.5", "41", "42","42.5", "43","44","44.5","45");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
    }

    private void setupAddToCartButton() {
        addToCartButton.setOnClickListener(v -> {
            if (currentShoe == null) {
                Toast.makeText(this, "Error: Shoe details not loaded", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedPosition = sizeSpinner.getSelectedItemPosition();
            if (selectedPosition == 0) {
                Toast.makeText(this, "Please select a size", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedColor == null) {
                Toast.makeText(this, "Please select a color", Toast.LENGTH_SHORT).show();
                return;
            }

            double selectedSize = Double.parseDouble(sizeSpinner.getSelectedItem().toString());
            CartItem cartItem = new CartItem(currentShoe, selectedSize, selectedColor);

            SharedPreferencesUtil.AddToCart(this, cartItem);

            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        });
    }

}
