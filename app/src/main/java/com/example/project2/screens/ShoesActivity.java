package com.example.project2.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ShoesAdapter;
import com.example.project2.models.Shoe;
import com.example.project2.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class ShoesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToAddShoe;
    private ImageButton cartButton; // הוספנו משתנה לכפתור העגלה
    private RecyclerView recyclerView;
    private ShoesAdapter shoesAdapter;
    private List<Shoe> shoesList = new ArrayList<>();
    private DatabaseService databaseService;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes); // שם ה-XML של ה-Activity

        databaseService = DatabaseService.getInstance();

        recyclerView = findViewById(R.id.shoesRecyclerView);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            // טיפול במצב שבו ה-RecyclerView לא נמצא
            Log.e("CartActivity", "RecyclerView לא נמצא! יש לבדוק את ה-XML וה-ID");
            // אפשר גם להציג Toast או פעולה אחרת:
            Toast.makeText(this, "לא ניתן למצוא את ה-RecyclerView", Toast.LENGTH_SHORT).show();
        }

        // הגדרת ה-Adapter עם רשימת הנעליים
        shoesAdapter = new ShoesAdapter(this, shoesList);
        recyclerView.setAdapter(shoesAdapter);

        btnToAddShoe = findViewById(R.id.btn_to_add_shoe);
        btnToAddShoe.setOnClickListener(this);

        // הגדרת כפתור העגלה
        cartButton = findViewById(R.id.cart_button);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoesActivity.this, CartActivity.class);
                startActivity(intent);
            }
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
