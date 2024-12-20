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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignup, btnLogin, btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         initviews();
    }

    private void initviews() {
        btnLogin=findViewById(R.id.btnGoLogin);
        btnSignup=findViewById(R.id.btnGoSignup);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btn=findViewById(R.id.add_to_cart_button);
        btn.setOnClickListener(this);

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
        if(v==btn){
            Intent add_to_cart_button= new Intent(this,AddToCartActivity.class);
            startActivity(add_to_cart_button);
        }


    }


}