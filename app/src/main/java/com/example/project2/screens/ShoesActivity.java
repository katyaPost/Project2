package com.example.project2.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ShoesAdapter;
import com.example.project2.models.Shoe;
import com.example.project2.services.AuthenticationService;
import com.example.project2.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class ShoesActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnToAddShoe;
    private ImageButton cartButton;
    private AppCompatImageView userButton; // 🔹 כפתור למעבר לעמוד המשתמש
    private RecyclerView recyclerView;
    private ShoesAdapter shoesAdapter;
    private List<Shoe> shoesList = new ArrayList<>();
    private DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);

        if (!AuthenticationService.getInstance().isUserSignedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        databaseService = DatabaseService.getInstance();

        recyclerView = findViewById(R.id.shoesRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // הגדרת ה-Adapter עם רשימת הנעליים
        shoesAdapter = new ShoesAdapter(this, shoesList);
        recyclerView.setAdapter(shoesAdapter);

        btnToAddShoe = findViewById(R.id.btn_to_add_shoe);
        btnToAddShoe.setOnClickListener(this);

        // 🔹 כפתור העגלה - מעבר ל-CartActivity
        cartButton = findViewById(R.id.cart_button);
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoesActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // 🔹 כפתור המשתמש - מעבר ל-UserInfoActivity
        userButton = findViewById(R.id.user_btn);
        userButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoesActivity.this, user_info.class);
            startActivity(intent);
        });

        // טעינת רשימת הנעליים מהמסד נתונים
        databaseService.getShoes(new DatabaseService.DatabaseCallback<List<Shoe>>() {
            @Override
            public void onCompleted(List<Shoe> shoes) {
                shoesList.clear();
                shoesList.addAll(shoes);
                shoesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                // ניתן להוסיף טיפול בשגיאות כאן
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnToAddShoe.getId()) {
            Intent intent = new Intent(this, AddShoeActivity.class);
            startActivity(intent);
        }
    }

}
