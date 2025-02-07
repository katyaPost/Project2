package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.ShoesAdapter;
import com.example.project2.models.Shoe;
import com.example.project2.services.DatabaseService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShoesActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnToAddShoe;

    private RecyclerView recyclerView;
    private ShoesAdapter shoesAdapter;
    private List<Shoe> shoesList = new ArrayList<>();

    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes); // שם ה-XML של ה-Activity

        databaseService = DatabaseService.getInstance();

        recyclerView = findViewById(R.id.shoesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // הגדרת ה-Adapter עם רשימת הנעליים
        shoesAdapter = new ShoesAdapter(this, shoesList);
        recyclerView.setAdapter(shoesAdapter);

        btnToAddShoe = findViewById(R.id.btn_to_add_shoe);
        btnToAddShoe.setOnClickListener(this);


        databaseService.getShoes(new DatabaseService.DatabaseCallback<List<Shoe>>() {
            @Override
            public void onCompleted(List<Shoe> shoes) {
                shoesList.clear();
                shoesList.addAll(shoes);
                shoesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnToAddShoe.getId()) {
            Intent intent = new Intent(this, AddShoeActivity.class);
            startActivity(intent);
            return;
        }
    }
}
