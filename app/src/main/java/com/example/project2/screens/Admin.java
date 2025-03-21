package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.R;

public class Admin extends AppCompatActivity implements View.OnClickListener {
    Button btnAbout,btntest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

    }
    private void initViews() {

        btntest= findViewById(R.id.btnGotest);
        btntest.setOnClickListener(this);
        btnAbout = findViewById(R.id.btnGoAbout);
        btnAbout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v==btntest){

            Intent goAdd=new Intent(getApplicationContext(), DeleteUserActivity.class);
            startActivity(goAdd);
        }

    }
}