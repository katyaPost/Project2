package com.example.project2.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.R;
import com.example.project2.models.User;
import com.example.project2.services.AuthenticationService;
import com.example.project2.services.DatabaseService;
import com.example.project2.utils.SharedPreferencesUtil;
import com.google.android.material.textfield.TextInputEditText;

public class user_info extends AppCompatActivity {

    private TextView tvUserName, tvUserLastName, tvUserEmail, tvUserPassword;
    private ImageButton logoutButton;

    TextInputEditText tvetUserFirstName;

    User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info); // טעינת ה-XML הנכון

        // חיבור רכיבים ל-ID שלהם
        tvUserName = findViewById(R.id.userName);
        tvUserLastName = findViewById(R.id.userLastName);
        tvUserEmail = findViewById(R.id.userEmail);
        tvUserPassword = findViewById(R.id.userPassword);
        logoutButton = findViewById(R.id.logoutButton);

        String userId = AuthenticationService.getInstance().getCurrentUserId();
        if(userId == null) {
            Toast.makeText(this, "ahhhhhh!!!!!!!!!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        DatabaseService.getInstance().getUser(userId, new DatabaseService.DatabaseCallback<User>() {
            @Override
            public void onCompleted(User user) {
                setView(user);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

        // לחיצה על כפתור התנתקות
        logoutButton.setOnClickListener(v -> {
            // מחיקת נתוני המשתמש מהזיכרון
            AuthenticationService.getInstance().signOut();
            SharedPreferencesUtil.clearCart(this);

            // מעבר למסך ההתחברות (LogIn)
            Intent intent = new Intent(user_info.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }


    private void setView(User user) {
        this.user = user;
        tvetUserFirstName.setText(user.getfName()+"");

    }
}
