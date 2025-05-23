package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignup, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initviews();
    }

    private void initviews() {
        btnLogin=findViewById(R.id.btnGoLogin);
        btnSignup=findViewById(R.id.btnGoSignup);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==btnSignup) {
            Intent btnGoSignup = new Intent(this, Register.class);
            startActivity(btnGoSignup);
        }
        if(v==btnLogin){
            Intent btnGoLogin= new Intent(this,LogIn.class);
            startActivity(btnGoLogin);
        }


    }


}